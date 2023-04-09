package com.asdc.pawpals.service.implementation;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.asdc.pawpals.Enums.AppointmentStatus;
import com.asdc.pawpals.Enums.ProfileStatus;
import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.VetAppointmentDto;
import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.dto.VetScheduleDto;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.NoAppointmentExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.model.VetAvailability;
import com.asdc.pawpals.repository.AppointmentRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetAvailabilityRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.MailTemplates;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.validators.AppointmentValidators;

@Component
@Lazy
public class VetServiceImpl implements VetService {
  @Autowired
  VetRepository vetRepository;

  @Autowired
  VetAvailabilityRepository vetAvailabilityRepository;

  @Autowired
  AppointmentRepository appointmentRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MailServiceImpl mailService;

  @Value("${pawpals.serve.base.url}")
  String serveUrl;

  /**

   * Registers a new vet in the system.
   * @param vetDto the VetDto object containing the details of the vet to be registered
   * @return a Boolean value indicating whether the vet was successfully registered or not
   * @throws UsernameNotFoundException if the provided username is not found in the user repository
*/
  @Override
  public Boolean registerVet(VetDto vetDto) throws UsernameNotFoundException {
    Boolean vetRegistered = false;
    Optional<User> user = null;
    if (vetDto != null) {
      Vet vet = Transformations.DTO_TO_MODEL_CONVERTER.vet(vetDto);
      //check if user with given username exists, then only register that user as vet
      user = userRepository.findById(vetDto.getUsername());
      if (!user.isEmpty()) {
        Long oldCount = vetRepository.count();
        vet.setProfileStatus(AppointmentStatus.PENDING.getLabel());
        vetRepository.save(vet);

        Long newCount = vetRepository.count();
        vetRegistered = ((oldCount + 1) == newCount);
      } else {
        throw new UsernameNotFoundException("Please provide a valid username");
      }
      if(vetRegistered){
        String subject = "Profile created @ PawPals";
        String body = MailTemplates.getVetRegistrationConfirmationString(vetDto.getFirstName()+" "+vetDto.getLastName());
        String to = user.get().getEmail();
        mailService.sendMail(to, subject, body);
      }
    }
    return vetRegistered;
  }

  /**

   * This method retrieves the list of vet availabilities for the specified user id.
   * @param userId the user id of the vet whose availabilities are to be retrieved
   * @return a list of VetAvailabilityDto objects representing the vet's availabilities, or null if none exist
*/
  @Override
  public List<VetAvailabilityDto> getVetAvailability(String userId) {
    List<VetAvailability> availability = vetAvailabilityRepository.findByVet_User_UserId(
      userId
    );
    List<VetAvailabilityDto> availabilityDto = null;
    if (availability != null) {
      availabilityDto =
        availability
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
          .collect(Collectors.toList());
    }
    return availabilityDto;
  }

  /**

Overrides the method postVetAvailability which accepts a list of VetAvailabilityDto objects and returns a list of VetAvailabilityDto objects.
This method validates if the input list of VetAvailabilityDto objects is not null or empty, and all the objects have the same vet user id.
Then, it finds the user and vet corresponding to the vet user id in the input, and saves the vet availability objects to the database.
Finally, it returns the saved VetAvailabilityDto objects.
@param availability A list of VetAvailabilityDto objects to be added to the database
@return The list of saved VetAvailabilityDto objects
@throws InvalidObjectException If the input list is null or empty or not all objects have the same vet user id
@throws UsernameNotFoundException If the user id provided in the input does not exist in the database
@throws UserNameNotFound If the vet user id provided in the input does not exist in the database
*/

  @Override
  public List<VetAvailabilityDto> postVetAvailability(
    List<VetAvailabilityDto> availability
  )
    throws InvalidObjectException, UsernameNotFoundException, UserNameNotFound {
    List<VetAvailabilityDto> returnAvailability = null;
    if (availability != null && !availability.isEmpty()) {
      String vetUserId = availability.get(0).getVetUserId();
      Boolean allVetUserIdSame = availability
        .stream()
        .allMatch(avl -> avl.getVetUserId().equals(vetUserId));
      if (allVetUserIdSame) {
        Optional<User> uOptional = userRepository.findById(vetUserId);
        Optional<Vet> vet = vetRepository.findByUser_UserId(vetUserId);
        if (uOptional.isPresent() && vet.isPresent()) {
          List<VetAvailability> vetAvailabilities = availability
            .stream()
            .map(Transformations.DTO_TO_MODEL_CONVERTER::vetAvailability)
            .map(
              avl -> {
                avl.setVet(vet.get());
                return avl;
              }
            )
            .collect(Collectors.toList());
          vetAvailabilities =
            vetAvailabilityRepository.saveAll(vetAvailabilities);
          returnAvailability =
            vetAvailabilities
              .stream()
              .map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
              .collect(Collectors.toList());
        } else {
          throw new UsernameNotFoundException(
            "Please provide a valid username"
          );
        }
      } else {
        throw new InvalidObjectException(
          "Vet User id should match for all availabilities"
        );
      }
    }
    return returnAvailability;
  }

  /**

Retrieves the list of available time slots of a vet identified by the provided vetId.
@param vetId the ID of the vet for which the availability needs to be retrieved
@return a list of {@link VetAvailabilityDto} representing the available time slots of the vet
@throws UserNameNotFound if the provided vetId does not match any existing vet in the system
*/
  @Override
  public List<VetAvailabilityDto> getVetAvailability(Long vetId) {
    List<VetAvailability> availability = vetAvailabilityRepository.findByVetId(
      vetId
    );
    List<VetAvailabilityDto> availabilityDto = null;
    if (availability != null) {
      availabilityDto =
        availability
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
          .collect(Collectors.toList());
    }
    return availabilityDto;
  }

  /**

Retrieves the availability of a veterinarian with the specified user ID on a specific date, by searching for their availability in the database and checking if they have any appointments on that date.
@param userId the user ID of the veterinarian whose availability to retrieve
@param date the date to check for availability
@return the {@code VetAvailabilityDto} containing the veterinarian's availability information for the specified date
*/
  @Override
  public VetAvailabilityDto getVetAvailabilityOnSpecificDay(
    String userId,
    String date
  ) {
    List<VetAvailability> availability = vetAvailabilityRepository.findByVet_User_UserId(
      userId
    );
    List<Appointment> appointments = appointmentRepository.findByVet_User_UserId(
      userId
    );
    return findVetAvailabilityOnSpecificDay(availability, appointments, date);
  }

  /**

Retrieves the availability of a specific vet on a particular day by querying
the vet availability and appointment repositories. Returns a DTO representing
the vet's availability on the given day.
@param vetId The ID of the vet whose availability is being queried.
@param date The date for which the availability is being queried.
@return A DTO representing the vet's availability on the given day.
*/
  @Override
  public VetAvailabilityDto getVetAvailabilityOnSpecificDay(
    Long vetId,
    String date
  ) {
    List<VetAvailability> availability = vetAvailabilityRepository.findByVetId(
      vetId
    );
    List<Appointment> appointments = appointmentRepository.findByVetId(vetId);
    return findVetAvailabilityOnSpecificDay(availability, appointments, date);
  }

  /**

Retrieves the schedule of a vet for a specific day.
@param userId the user id of the vet
@param date the date for which to retrieve the schedule
@return a VetScheduleDto object containing the user id of the vet, and a list of pairs representing booked time slots
*/
  @Override
  public VetScheduleDto getVetScheduleOnSpecificDay(
    String userId,
    String date
  ) {
    List<Appointment> appointments = appointmentRepository.findByVet_User_UserId(
      userId
    );
    VetScheduleDto vetSchedule = new VetScheduleDto();
    vetSchedule.setVetUserId(userId);
    List<Pair<String, String>> slotsBooked = new ArrayList<>();
    if (appointments != null) {
      appointments
        .stream()
        .filter(Objects::nonNull)
        .filter(apt -> apt.getDate() != null)
        .filter(apt -> apt.getDate().equals(date))
        .filter(apt -> apt.getStatus() != null)
        .filter(
          apt -> apt.getStatus().equals(AppointmentStatus.CONFIRMED.getLabel())
        )
        .forEach(
          appointment -> {
            slotsBooked.add(
              Pair.of(appointment.getStartTime(), appointment.getEndTime())
            );
          }
        );
      vetSchedule.setSlotsBooked(slotsBooked);
    }
    return vetSchedule;
  }

  /**

Overrides the base method to change the status of an appointment given by its ID.
@param appointmentDto appointment object containing updated details
@param id ID of the appointment to be updated
@return appointment object with updated details
@throws InvalidAppointmentId if appointment with the given ID is not found
*/
  @Override
  public AppointmentDto changeStatus(AppointmentDto appointmentDto, Integer id)
    throws InvalidAppointmentId {
    Appointment appointment = appointmentRepository
      .findById(id)
      .orElseThrow(InvalidAppointmentId::new);
    Appointment returnedAppointment = null;
    if (appointmentDto.getStatus() != null) {
      appointment.setStatus(appointmentDto.getStatus());
    }
    if (appointmentDto.getDate() != null) {
      appointment.setDate(appointmentDto.getDate());
    }
    if (appointmentDto.getStartTime() != null) {
      appointment.setStartTime(appointmentDto.getStartTime());
    }
    if (appointmentDto.getEndTime() != null) {
      appointment.setEndTime(appointmentDto.getEndTime());
    }
    /**
     * Old Code
     * 
     * 
     if (
      AppointmentValidators.isValidAppointment(
        appointment.getDate(),
        appointment.getStartTime(),
        appointment.getEndTime(),
        appointment.getStatus()
      )

     * Major Modification 
     * TEST THIS 
     */
    if (isAppointmentValid(appointment)) {
      returnedAppointment = appointmentRepository.saveAndFlush(appointment);
      PetOwner owner = appointment.getAnimal().getOwner();
      if(appointment.getStatus().equals(AppointmentStatus.CONFIRMED.getLabel())){
          String subject = "Appointment Confirmed @ PawPals";
          String body = MailTemplates.getAppointmentConfirmationString(owner.getFirstName()+" "+owner.getLastName(), appointment.getDate(), appointment.getStartTime(), appointment.getVet().getClinicAddress(), serveUrl);
          String to = owner.getUser().getEmail();
          mailService.sendMail(to, subject, body);
      }
      else if(appointment.getStatus().equals(AppointmentStatus.REJECTED.getLabel())){
        String subject = "Appointment Rejected @ PawPals";
        String body = MailTemplates.getAppointmentCancelString(owner.getFirstName()+" "+owner.getLastName(), appointment.getDate(), appointment.getStartTime());
        String to = owner.getUser().getEmail();
        mailService.sendMail(to, subject, body);
      }
    }
    return Transformations.MODEL_TO_DTO_CONVERTER.appointment(
      returnedAppointment
    );
  }

  private boolean isAppointmentValid(Appointment appointment) {
    return AppointmentValidators.isValidAppointment(
      appointment.getDate(),
      appointment.getStartTime(),
      appointment.getEndTime(),
      appointment.getStatus()
    );
  }

  /**

Retrieves all appointments with their respective pet and pet owner details for a given vet.
@param vetId The ID of the vet for whom the appointments are being retrieved.
@return A list of {@link VetAppointmentDto} containing details of all appointments for the given vet.
@throws UserNameNotFound If the provided vet ID does not correspond to any existing vet.
@throws NoAppointmentExist If the given vet does not have any appointments.
*/
  @Override
  public List<VetAppointmentDto> retrieveAllPets(String vetId)
    throws UserNameNotFound, NoAppointmentExist {
    Vet vet = vetRepository
      .findByUser_UserId(vetId)
      .orElseThrow(UserNameNotFound::new);

    if (vet.getAppointment().isEmpty()) {
      throw new NoAppointmentExist("No appointment exist for vet" + vetId);
    }

    return vet
      .getAppointment()
      .stream()
      .map(
        appointment -> {
          VetAppointmentDto vetAppointmentDto = new VetAppointmentDto();
          vetAppointmentDto.setAppointmentDto(
            Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment)
          );
          vetAppointmentDto.setAnimalDto(
            Transformations.MODEL_TO_DTO_CONVERTER.animal(
              appointment.getAnimal()
            )
          );
          vetAppointmentDto.setPetOwnerDto(
            Transformations.MODEL_TO_DTO_CONVERTER.petOwner(
              appointment.getAnimal().getOwner()
            )
          );
          vetAppointmentDto.setMedicalHistoryDtos(
            appointment
              .getAnimal()
              .getMedicalHistories()
              .stream()
              .map(
                m ->
                  Transformations.MODEL_TO_DTO_CONVERTER.medicalHistoryInline(m)
              )
              .collect(Collectors.toList())
          );
          return vetAppointmentDto;
        }
      )
      .collect(Collectors.toList());
  }

  /**

Retrieves all the vets with their profiles in "pending" status and maps them to the DTO objects.
@return List of {@link VetDto} objects containing the details of all the vets with "pending" profile status.
*/
  @Override
  public List<VetDto> retrieveAllVets() {
    return vetRepository
      .findAll()
      .stream()
      .filter(
        v -> v.getProfileStatus().equals(AppointmentStatus.PENDING.getLabel())
      )
      .map(v -> Transformations.MODEL_TO_DTO_CONVERTER.vet(v))
      .collect(Collectors.toList());
  }

  /**
   * @param vetDto
   * @param id
   * @return
   */
  @Override
  public VetDto updateVet(VetDto vetDto, String id, MultipartFile image)
    throws UserNameNotFound, IOException, InvalidImage {
    /**
     * Old code
     * if (null != id && !id.isEmpty() && vetDto != null)
     */
    boolean isValid = (null != id && !id.isEmpty());
    if (isValid && vetDto != null) {
      Vet vet = vetRepository
        .findByUser_UserId(id)
        .orElseThrow(UserNameNotFound::new);
      if (vetDto.getClinicAddress() != null) {
        vet.setClinicAddress(vetDto.getClinicAddress());
      }
      if (null != image && vetDto.getClinicUrl() != null) {
        vet.setClinicUrl(CommonUtils.getBytes(image));
      }
      if (vetDto.getExperience() != null) {
        vet.setExperience(vetDto.getExperience());
      }
      if (vetDto.getQualification() != null) {
        vet.setQualification(vetDto.getQualification());
      }
      if (vetDto.getProfileStatus() != null) {
        if (
          vetDto.getProfileStatus().equals(ProfileStatus.APPROVED.getLabel())
        ) {
          mailService.sendMail(
            vet.getUser().getEmail(),
            "Successfully Approved",
            "your profile is successfully approved by Admin"
          );
        } else if (
          vetDto.getProfileStatus().equals(ProfileStatus.REJECTED.getLabel())
        ) {
          mailService.sendMail(
            vet.getUser().getEmail(),
            "Profile Decline",
            "Unfortunately your profile has been rejected by Admin"
          );
        }
        vet.setProfileStatus(vetDto.getProfileStatus());
      }
      if (vetDto.getLicenseNumber() != null) {
        vet.setLicenseNumber(vetDto.getLicenseNumber());
      }
      if (vetDto.getFirstName() != null) {
        vet.setFirstName(vetDto.getFirstName());
      }
      if (vetDto.getLastName() != null) {
        vet.setLastName(vetDto.getLastName());
      }
      if (vetDto.getClinicAddress() != null) {
        vet.setClinicUrl(vet.getClinicUrl());
      }

      vetRepository.saveAndFlush(vet);
      return Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else if (vetDto == null) {
      throw new InvalidObjectException("Invalid Vet object body");
    } else {
      throw new UserNameNotFound("User name is not found for " + id);
    }
  }

  /**
   * @param id
   * @return
   */
  @Override
  public VetDto getVetByUserId(String id) throws UserNameNotFound {
    if (
      null != id &&
      !id.isEmpty() &&
      vetRepository.findByUser_UserId(id).isPresent()
    ) {
      Vet vet = vetRepository.findByUser_UserId(id).get();
      return Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else {
      throw new UserNameNotFound("No vet exist by id: " + id);
    }
  }

  /**

Updates the profile status of a veterinarian with the given ID, based on the
information contained in the provided VetDto object. If the ID or VetDto object are null,
or if a veterinarian with the given ID cannot be found, an exception is thrown.
If the provided VetDto object contains a non-null profile status, the corresponding veterinarian's
profile status is updated to that value. If the profile status is updated to "CONFIRMED", an email
is sent to the veterinarian indicating that their profile has been approved by an administrator.
If the profile status is updated to "REJECTED", an email is sent to the veterinarian indicating that
their profile has been rejected by an administrator.
@param vetDto the VetDto object containing the updated profile status information
@param id the ID of the veterinarian to update
@return a VetDto object representing the updated veterinarian
@throws UserNameNotFound if a veterinarian with the given ID cannot be found
@throws InvalidObjectException if the provided VetDto object is null
*/
  @Override
  public VetDto updateProfileStatus(VetDto vetDto, String id)
    throws UserNameNotFound, InvalidObjectException {
    if (null != id && !id.isEmpty() && vetDto != null) {
      Vet vet = vetRepository
        .findByUser_UserId(id)
        .orElseThrow(UserNameNotFound::new);

      if (vetDto.getProfileStatus() != null) {
        vet.setProfileStatus(vetDto.getProfileStatus());
        vet = vetRepository.saveAndFlush(vet);

        if (
          vetDto
            .getProfileStatus()
            .equals(ProfileStatus.APPROVED.getLabel())
        ) {
          String subject = "Profile Approved @ PawPals";
          String body = MailTemplates.getVetApprovedMessageString(vet.getFirstName()+" "+vet.getLastName(), serveUrl);
          String to = vet.getUser().getEmail();
          mailService.sendMail(to, subject, body);
          
        } else if (
          vetDto
            .getProfileStatus()
            .equals(ProfileStatus.REJECTED.getLabel())
        ) {
          String subject = "Profile Rejected @ PawPals";
          String body = MailTemplates.getVetRejectedMessageString(vet.getFirstName()+" "+vet.getLastName());
          String to = vet.getUser().getEmail();
          mailService.sendMail(to, subject, body);
        }
      }
      return Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else if (vetDto == null) {
      throw new InvalidObjectException("Invalid pet owner object body");
    } else {
      throw new UserNameNotFound("User name is not found for " + id);
    }
  }

  /**

Finds the vet availability on a specific day based on the given date, list of vet availabilities and appointments.
@param availability a list of VetAvailability objects representing the vet's availability
@param appointments a list of Appointment objects representing the vet's booked appointments
@param date a string representing the date for which to find availability
@return a VetAvailabilityDto object representing the vet's availability on the specific day with free slots, or null if no availability found on the day
*/
  private VetAvailabilityDto findVetAvailabilityOnSpecificDay(
    List<VetAvailability> availability,
    List<Appointment> appointments,
    String date
  ) {
    VetAvailabilityDto availabilityDto = null;
    if (availability != null) {
      //get the original vet availability on the specific day mentioned (derived from date given)
      availabilityDto =
        availability
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
          .filter(
            avl ->
              avl
                .getDayOfWeek()
                .equalsIgnoreCase(CommonUtils.getDayFromDate(date))
          )
          .findFirst()
          .orElse(null);

      if (availabilityDto != null) {
        List<Pair<String, String>> freeSlots = new ArrayList<>();
        String starTime = availabilityDto.getSlots().get(0).getFirst();
        String endTime = availabilityDto.getSlots().get(0).getSecond();

        String currentSlot = starTime;
        while (!currentSlot.equals(endTime)) {
          final String fCurrSlot = currentSlot;
          Boolean appointmentBooked = appointments
            .stream()
            .filter(Objects::nonNull)
            .filter(apt -> apt.getDate() != null)
            .filter(apt -> apt.getDate().equals(date))
            .filter(apt -> apt.getStatus() != null)
            .filter(
              apt ->
                apt.getStatus().equals(AppointmentStatus.CONFIRMED.getLabel())
            )
            .anyMatch(
              apt -> {
                return apt.getStartTime().equals(fCurrSlot);
              }
            );
          if (!appointmentBooked) {
            freeSlots.add(
              Pair.of(currentSlot, CommonUtils.getNextSlotTime(currentSlot))
            );
          }
          currentSlot = CommonUtils.getNextSlotTime(currentSlot);
        }

        availabilityDto.setSlots(freeSlots);
      }
    }
    return availabilityDto;
  }
}

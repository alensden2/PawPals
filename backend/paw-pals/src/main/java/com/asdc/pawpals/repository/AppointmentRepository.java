package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository
  extends JpaRepository<Appointment, Integer> {
  public List<Appointment> findByVet_User_UserId(String userId);

  public List<Appointment> findByVetId(Long id);
}

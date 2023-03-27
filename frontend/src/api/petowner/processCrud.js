/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import {
  registerPetOwnerApiCall,
  getAllMedicalHistoryOfPetApiCall
} from './crud';

export const registerPetOwner = async (petOwner) => {
  const response = await registerPetOwnerApiCall(petOwner);
  return response;
};

export const getAllMedicalHistoryOfPet = async ({ petOwnerUserId }) => {
  const response = await getAllMedicalHistoryOfPetApiCall({ petOwnerUserId });
  const body = response?.data?.body;

  if (body) {
    return body.map((item) => ({
      vet: item.vetDto,
      animal: item.animalDto,
      medicalHistory: item.medicalHistoryDto
    }));
  } else return [];
};

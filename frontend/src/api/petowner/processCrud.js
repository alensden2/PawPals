/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerPetOwnerApiCall,
  getAllMedicalHistoryOfPetApiCall,
  getAllPetsApiCall
} from './crud';
import { bytesToImageUrl } from '@src/utils';

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

export const getAllPets = async () => {
  const response = await getAllPetsApiCall();
  const body = response?.data?.body || [];

  return body.map((item) => ({
    ...item,
    photoUrl: item.photoUrl ? bytesToImageUrl(item.photoUrl) : ''
  }));
};

/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerPetOwnerApiCall,
  getAllMedicalHistoryOfPetApiCall,
  getAllPetsApiCall
} from './crud';
import { getImageUrlFromBytes } from '@src/utils';

export const registerPetOwner = async (petOwner) => {
  const response = await registerPetOwnerApiCall(petOwner);
  return response;
};

export const getAllMedicalHistoryOfPet = async () => {
  const response = await getAllMedicalHistoryOfPetApiCall();
  const body = response?.data?.body;

  if (body) {
    return body.map((item) => ({
      vet: item.vetDto,
      animal: {
        ...item.animalDto,
        photoUrl: item.animalDto.photoUrl
          ? getImageUrlFromBytes({ bytes: item.animalDto.photoUrl })
          : ''
      },
      medicalHistory: item.medicalHistoryDto
    }));
  } else return [];
};

export const getAllPets = async () => {
  const response = await getAllPetsApiCall();
  const body = response?.data?.body || [];

  return body.map((item) => ({
    ...item,
    photoUrl: item.photoUrl
      ? getImageUrlFromBytes({ bytes: item.photoUrl })
      : ''
  }));
};

export const getAllPetsForUser = async (userName) => {
  const response = await getAllPetsApiCall({ ownerUserId: userName });
  const body = response?.data?.body || [];

  return body.map((item) => ({
    ...item,
    photoUrl: item.photoUrl
      ? getImageUrlFromBytes({ bytes: item.photoUrl })
      : ''
  }));
};

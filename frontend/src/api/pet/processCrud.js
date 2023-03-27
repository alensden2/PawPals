import {
  createPetApiCall
  // deletePetApiCall,
  // updatePetApiCall,
  // getAllPetsApiCall
} from './crud';

import { bytesToImageUrl } from '@src/utils';

export const createPet = async ({ input = {} } = {}) => {
  const response = await createPetApiCall({ input });
  const body = response?.data?.body || [];
  const photoUrl = body.photoUrl;

  const url = photoUrl ? bytesToImageUrl(photoUrl) : '';

  return {
    ...body,
    photoUrl: url
  };
};

export const getAllPets = async ({}) => {
  console.error('getAllPets');
};

export const deletePet = async ({}) => {
  console.error('deletePet');
};

export const updatePet = async ({}) => {
  console.error('updatePet');
};

/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  createPetApiCall
  // deletePetApiCall,
  // updatePetApiCall,
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

export const deletePet = async ({}) => {
  console.error('deletePet');
};

export const updatePet = async ({}) => {
  console.error('updatePet');
};

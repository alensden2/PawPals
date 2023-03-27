/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosFORM as axios, axiosJSON } from '@src/lib';

export const createPetApiCall = async ({ input = {} } = {}) => {
  try {
    const formData = new FormData();

    const animal = {
      name: input.name,
      type: input.type,
      age: input.age,
      gender: input.gender,
      ownerId: 'ishan' //  TODO: get this ownerId from localStorage
    };

    formData.append(
      'animal',
      new Blob([JSON.stringify(animal)], { type: 'application/json' })
    );

    if (input.photoUrl) {
      formData.append('image', input.photoUrl);
    }

    return await axios.post('/unauth/animal/register', formData);
  } catch (e) {
    console.error(e);
  }
};

export const getAllPetsApiCall = async ({ ownerUserId }) => {
  try {
    return await axiosJSON.get('/unauth/animal/pets', { ownerId: ownerUserId });
  } catch (e) {
    console.error(e);
  }
};

export const updatePetApiCall = async ({ petId, input }) => {
  try {
    return await axiosJSON.put(`/unauth/animal/${petId}`, input);
  } catch (e) {
    console.error(e);
  }
};

export const deletePetApiCall = async ({ petId }) => {
  try {
    return await axiosJSON.delete(`/unauth/animal/${petId}`);
  } catch (e) {
    console.error(e);
  }
};

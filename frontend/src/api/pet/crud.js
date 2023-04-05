/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosFORM, axiosJSON } from '@src/lib';
import { localStorageUtil } from '@src/utils';

export const createPetApiCall = async ({ input = {} } = {}) => {
  try {
    const formData = new FormData();
    const user = localStorageUtil.getItem('user');
    const petOwnerId = user.userName;

    const animal = {
      name: input.name,
      type: input.type,
      age: input.age,
      gender: input.gender,
      ownerId: petOwnerId
    };

    formData.append(
      'animal',
      new Blob([JSON.stringify(animal)], { type: 'application/json' })
    );

    if (input.photoUrl) {
      formData.append('image', input.photoUrl);
    }

    return await axiosFORM.post('/unauth/animal/register', formData);
  } catch (e) {
    console.error(e);
  }
};

export const updatePetApiCall = async ({ petId, input }) => {
  try {
    const formData = new FormData();
    const user = localStorageUtil.getItem('user');
    const petOwnerId = user.userName;

    const animal = {
      name: input.name,
      type: input.type,
      age: input.age,
      gender: input.gender,
      ownerId: petOwnerId
    };

    formData.append(
      'animal',
      new Blob([JSON.stringify(animal)], { type: 'application/json' })
    );

    if (input.photoUrl) {
      formData.append('image', input.photoUrl);
    }

    return await axiosFORM.put(`/unauth/animal/${petId}`, formData);

    // return await axiosFORM.put(`/unauth/animal/${petId}`, input);
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

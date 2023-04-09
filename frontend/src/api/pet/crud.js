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

    return await axiosFORM.post('/auth/animal/register', formData);
  } catch (e) {
    console.error(e);
  }
};

export const updatePetApiCall = async ({ petId, input }) => {
  try {
    const user = localStorageUtil.getItem('user');
    const petOwnerId = user.userName;

    const animal = {
      name: input.name,
      type: input.type,
      age: input.age,
      gender: input.gender,
      ownerId: petOwnerId
    };

    if (input.photoUrl) {
      const formData = new FormData();

      formData.append(
        'animal',
        new Blob([JSON.stringify(animal)], { type: 'application/json' })
      );

      if (input.photoUrl) {
        formData.append('image', input.photoUrl);
      }

      return await axiosFORM.put(`/auth/animal/${petId}`, formData);
    } else {
      return await axiosJSON.put(`/auth/animal/animal-obj/${petId}`, animal);
    }
  } catch (e) {
    console.error(e);
  }
};

export const deletePetApiCall = async ({ petId }) => {
  try {
    return await axiosJSON.delete(`/auth/animal/${petId}`);
  } catch (e) {
    console.error(e);
  }
};

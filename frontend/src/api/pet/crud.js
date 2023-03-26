/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosFORM as axios } from '@src/lib';

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

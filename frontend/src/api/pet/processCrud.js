import {
  createPetApiCall
  // deletePetApiCall,
  // updatePetApiCall,
  // getAllPetsApiCall
} from './crud';

export const createPet = async ({ input = {} } = {}) => {
  await createPetApiCall({ input });
  // TODO: once id is resolved from backend, return data from here

  return {};
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

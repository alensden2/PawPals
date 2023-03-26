import { createPetApiCall } from './crud';

export const createPet = async ({ input = {} } = {}) => {
  await createPetApiCall({ input });
  // TODO: once id is resolved from backend, return data from here

  return {};
};

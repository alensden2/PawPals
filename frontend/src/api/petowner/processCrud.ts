import { PetOwner } from '@src/types';
import { registerPetOwnerApiCall } from './crud';

export const registerPetOwner = async (petOwner: PetOwner) => {
  const response = await registerPetOwnerApiCall(petOwner);
  return response;
};
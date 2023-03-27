/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import { registerPetOwnerApiCall } from './crud';

export const registerPetOwner = async (petOwner) => {
  const response = await registerPetOwnerApiCall(petOwner);
  return response;
};

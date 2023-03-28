/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { registerVetApiCall } from './crud';

export const registerVet = async (vet) => {
  const response = await registerVetApiCall(vet);
  return response;
};

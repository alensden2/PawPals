// @ts-nocheck

import { createMedicalRecordApiCall } from './crud';

export const createMedicalRecord = async ({ input = {} } = {}) => {
  const response = await createMedicalRecordApiCall({ input });
  const isSuccess = response.data.success;

  return isSuccess;
};

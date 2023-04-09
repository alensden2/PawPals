// @ts-nocheck

import { axiosJSON } from '@src/lib';

export const createMedicalRecordApiCall = async ({ input = {} } = {}) => {
  try {
    return await axiosJSON.post(
      '/auth/medical-history-management/create',
      input
    );
  } catch (e) {
    console.error(e);
  }
};

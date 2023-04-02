/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { createMedicalRecordApiCall } from './crud';

export const createMedicalRecord = async ({ input = {} } = {}) => {
  return await createMedicalRecordApiCall({ input });
};

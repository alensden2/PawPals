/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosJSON } from '@src/lib';

export const getAllVetsApiCall = async () => {
  try {
    return await axiosJSON.get(`/unauth/admin/all-vets`);
  } catch (e) {
    console.error(e);
  }
};

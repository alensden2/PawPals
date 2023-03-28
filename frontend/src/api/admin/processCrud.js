/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import { getAllVetsApiCall } from './crud';

export const getAllVets = async () => {
  const response = await getAllVetsApiCall();
  return response?.data || [];
};

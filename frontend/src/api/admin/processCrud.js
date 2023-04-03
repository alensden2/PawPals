/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import { getAllVetsApiCall } from './crud';
import { getImageUrlFromBytes } from '@src/utils';

export const getAllVets = async () => {
  const response = await getAllVetsApiCall();

  const data = response?.data || [];

  return data.map((item) => {
    return {
      ...item,
      clinicUrl: item.clinicUrl
        ? getImageUrlFromBytes({ bytes: item.clinicUrl })
        : ''
    };
  });
};

/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosJSON } from '@src/lib';


export const bookAppointmentApiCall = async (appointmentDetails) => {
    try {
        return await axiosJSON.post(`/unauth/appointment/book-appointment`, appointmentDetails);
    } catch (e) {
        console.error(e);
    }
}
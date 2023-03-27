/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import MedicalHistoryCardList from './MedicalRecordCardList';
import useStyles from './PetMedicalRecord.styles';
import { HeaderContext } from '@src/context';
import { getAllMedicalHistoryOfPet } from '@src/api';

const PetMedicalRecord = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [petMedicalRecord, setPetMedicalRecord] = useState([]);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Medical History',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });

    async function fetchData() {
      try {
        const result = await getAllMedicalHistoryOfPet({
          petOwnerUserId: 'ishan'
        });

        setPetMedicalRecord(result);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <MedicalHistoryCardList petMedicalRecord={petMedicalRecord} />
    </div>
  );
};

export default PetMedicalRecord;

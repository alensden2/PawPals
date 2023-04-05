/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import MedicalHistoryCardList from './MedicalRecordCardList';
import useStyles from './PetMedicalRecord.styles';
import { HeaderContext } from '@src/context';
import { getAllMedicalHistoryOfPet } from '@src/api';
import { Loader, EmptyState } from '@src/components';

const PetMedicalRecord = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [petMedicalRecord, setPetMedicalRecord] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

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
        setIsLoading(true);
        const result = await getAllMedicalHistoryOfPet();
        setIsLoading(false);

        setPetMedicalRecord(result);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (petMedicalRecord?.length === 0) {
      return <EmptyState text="No medical records found for your pets!" />;
    }

    return <MedicalHistoryCardList petMedicalRecord={petMedicalRecord} />;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default PetMedicalRecord;

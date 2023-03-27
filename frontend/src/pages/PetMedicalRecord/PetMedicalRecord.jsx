/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import MedicalHistoryCardList from './MedicalRecordCardList';
import useStyles from './PetMedicalRecord.styles';
import { HeaderContext } from '@src/context';
import { medHistoryData } from '@src/data';

const PetMedicalRecord = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [medicalRecord] = useState(medHistoryData);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Medical History',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <MedicalHistoryCardList medicalRecords={medicalRecord.medicalRecords} />
    </div>
  );
};

export default PetMedicalRecord;

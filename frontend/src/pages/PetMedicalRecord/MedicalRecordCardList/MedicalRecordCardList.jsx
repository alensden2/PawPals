/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import MedicalRecordCard from '../MedicalRecordCard';
import useStyles from './MedicalRecordCardList.styles';

const MedicalRecordCardList = ({ medicalRecords }) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      {medicalRecords.map((item) => (
        <MedicalRecordCard key={item.medicalHistoryId} medicalRecord={item} />
      ))}
    </div>
  );
};

export default MedicalRecordCardList;

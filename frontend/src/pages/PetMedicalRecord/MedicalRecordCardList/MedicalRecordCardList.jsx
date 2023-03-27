/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import MedicalRecordCard from '../MedicalRecordCard';
import useStyles from './MedicalRecordCardList.styles';

const MedicalRecordCardList = ({ petMedicalRecord }) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      {petMedicalRecord.map((item, idx) => (
        <MedicalRecordCard key={idx} medicalRecord={item} />
      ))}
    </div>
  );
};

export default MedicalRecordCardList;

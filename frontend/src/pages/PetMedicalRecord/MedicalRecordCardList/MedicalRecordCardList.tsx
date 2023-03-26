import React from 'react';
import { MedicalRecordList } from '@src/types';
import MedicalRecordCard from '../MedicalRecordCard';
import useStyles from './MedicalRecordCardList.styles';

const MedicalRecordCardList: React.FC<MedicalRecordList> = ({
  medicalRecords
}) => {
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

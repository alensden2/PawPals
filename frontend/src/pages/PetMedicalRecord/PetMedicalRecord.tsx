import React, { useContext, useEffect, useState } from 'react';
import MedicalHistoryCardList from './MedicalRecordCardList';
import useStyles from './PetMedicalRecord.styles';
import { MedicalRecordState } from '@src/types';
import { HeaderContext } from '@src/context';
import { medHistoryData } from '@src/data';

const PetMedicalRecord: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [medicalRecord] = useState<MedicalRecordState>(medHistoryData);

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

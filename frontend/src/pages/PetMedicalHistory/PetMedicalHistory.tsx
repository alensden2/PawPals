import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';
import { EmptyState } from '@src/components';

const PetMedicalHistory: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

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

  return <EmptyState />;
};

export default PetMedicalHistory;

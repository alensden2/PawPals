import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';
import { EmptyState } from '@src/components';

const PetOwnerAllVets: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Veterinarian',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

  return <EmptyState />;
};

export default PetOwnerAllVets;

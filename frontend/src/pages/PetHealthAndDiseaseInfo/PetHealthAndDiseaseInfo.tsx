import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';
import { EmptyState } from '@src/components';

const PetHealthAndDiseaseInfo: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Pet Health And Diseases',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <EmptyState />;
};

export default PetHealthAndDiseaseInfo;

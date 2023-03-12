import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';

const PetOwnerHome: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

  return <div>{'PetOwnerHome'}</div>;
};

export default PetOwnerHome;

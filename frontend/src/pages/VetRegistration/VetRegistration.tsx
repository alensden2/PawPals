import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';

const VetRegistration: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Vet Registration',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <div>{'VetRegistration'}</div>;
};

export default VetRegistration;

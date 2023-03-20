import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';

const VetHome: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <div>{'VetHome'}</div>;
};

export default VetHome;
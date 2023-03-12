import { useState } from 'react';
import { HeaderContext } from './context';
import { HeaderInput, HeaderContextType } from './type';

type HeaderContextProviderProps = {
  children: React.ReactNode;
};

export const HeaderContextProvider: React.FC<HeaderContextProviderProps> = ({
  children
}) => {
  const [headerState, setHeaderState] = useState<HeaderInput>({
    shouldShowBackButton: false,
    title: '',
    shouldShowLogoutButton: false
  });

  const value: HeaderContextType = {
    shouldShowBackButton: headerState.shouldShowBackButton,
    title: headerState.title,
    shouldShowLogoutButton: headerState.shouldShowLogoutButton,
    setHeader: (newHeader: HeaderInput) => {
      setHeaderState((prevHeader) => ({ ...prevHeader, ...newHeader }));
    }
  };

  return (
    <HeaderContext.Provider value={value}>{children}</HeaderContext.Provider>
  );
};

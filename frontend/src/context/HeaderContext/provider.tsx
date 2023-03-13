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
    shouldShowHeader: false,
    shouldShowBackButton: false,
    backRoute: '',
    title: '',
    shouldShowLogoutButton: false
  });

  const value: HeaderContextType = {
    shouldShowHeader: headerState.shouldShowHeader,
    shouldShowBackButton: headerState.shouldShowBackButton,
    title: headerState.title,
    backRoute: headerState.backRoute,
    shouldShowLogoutButton: headerState.shouldShowLogoutButton,
    setHeader: (newHeader: HeaderInput) => {
      setHeaderState((prevHeader) => ({ ...prevHeader, ...newHeader }));
    }
  };

  return (
    <HeaderContext.Provider value={value}>{children}</HeaderContext.Provider>
  );
};

import { createContext } from 'react';
import { HeaderContextType } from './type';

export const HeaderContext = createContext<HeaderContextType>({
  shouldShowHeader: false,
  shouldShowBackButton: false,
  title: '',
  shouldShowLogoutButton: false,
  setHeader: () => {
    throw new Error('setHeader function must be overridden');
  }
});

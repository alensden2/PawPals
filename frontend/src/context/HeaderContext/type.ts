export type HeaderContextType = {
  shouldShowBackButton: boolean;
  title: string;
  shouldShowLogoutButton: boolean;
  setHeader: (header: HeaderInput) => void;
};

export type HeaderInput = {
  shouldShowBackButton: boolean;
  title: string;
  shouldShowLogoutButton: boolean;
};

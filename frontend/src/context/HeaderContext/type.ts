export type HeaderContextType = {
  shouldShowHeader: boolean;
  shouldShowBackButton: boolean;
  title: string;
  shouldShowLogoutButton: boolean;
  setHeader: (header: HeaderInput) => void;
};

export type HeaderInput = {
  shouldShowHeader: boolean;
  shouldShowBackButton: boolean;
  title: string;
  shouldShowLogoutButton: boolean;
};

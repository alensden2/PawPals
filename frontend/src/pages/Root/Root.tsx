import React, { useContext } from 'react';
import { Toast, Header } from '@src/components';
import { ToastContext, HeaderContext } from '@src/context';
import { Outlet } from 'react-router-dom';

function Root() {
  const { toast } = useContext(ToastContext);
  const {
    shouldShowHeader,
    title,
    shouldShowBackButton,
    shouldShowLogoutButton
  } = useContext(HeaderContext);

  return (
    <div>
      {shouldShowHeader ? (
        <Header
          title={title}
          shouldShowBackButton={shouldShowBackButton}
          shouldShowLogoutButton={shouldShowLogoutButton}
        />
      ) : null}
      <Toast toast={toast} />
      <Outlet />
    </div>
  );
}

export default Root;

import React, { useContext } from 'react';
import { Toast, Header } from '@src/components';
import { ToastContext, HeaderContext } from '@src/context';
import { Outlet } from 'react-router-dom';
// styles
import useStyles from './Root.styles';

function Root() {
  const { toast } = useContext(ToastContext);
  const {
    shouldShowHeader,
    title,
    backRoute,
    shouldShowBackButton,
    shouldShowLogoutButton
  } = useContext(HeaderContext);

  const classes = useStyles();

  return (
    <div className={classes.root}>
      {shouldShowHeader ? (
        <Header
          title={title}
          backRoute={backRoute}
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

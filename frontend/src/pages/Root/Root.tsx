import React, { useContext } from 'react';
import { Toast } from '@src/components';
import { ToastContext } from '@src/context';
import { Outlet } from 'react-router-dom';

function Root() {
  const { toast } = useContext(ToastContext);

  return (
    <div>
      <Toast toast={toast} />
      <Outlet />
    </div>
  );
}

export default Root;

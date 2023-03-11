import { Snackbar } from '@material-ui/core';
import MuiAlert from '@material-ui/lab/Alert';

import React, { useState, useEffect } from 'react';
import { ToastMessage } from '@src/interfaces';

interface Props {
  toast: ToastMessage;
}

function Toast(props: Props) {
  const { type, message } = props.toast;
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (message) setOpen(true);
  }, [message]);

  function handleClose() {
    setOpen(false);
  }

  return (
    <Snackbar open={open} autoHideDuration={3000} onClose={handleClose}>
      <MuiAlert onClose={handleClose} severity={type}>
        {message}
      </MuiAlert>
    </Snackbar>
  );
}

export default Toast;

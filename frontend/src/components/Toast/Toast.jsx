import { Snackbar } from '@material-ui/core';
import MuiAlert from '@material-ui/lab/Alert';

import React, { useState, useEffect } from 'react';

// @ts-ignore
function ToastComponent(props) {
  const { type, message } = props.toast;
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (props.toast.message) setOpen(true);
  }, [props.toast]);

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

export default ToastComponent;

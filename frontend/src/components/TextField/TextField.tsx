import React, { ChangeEvent } from 'react';
import TextField from '@material-ui/core/TextField';
import { TextFieldProps } from '@mui/material';

interface CustomTextFieldProps  {
  label: string;
  type: string;
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
  fullWidth?: boolean;
  nativeProps?:TextFieldProps
}

const CustomTextField: React.FC<CustomTextFieldProps> = ({
  label,
  type,
  value,
  fullWidth = false,
  onChange,
  nativeProps
}) => {
  return (
    <TextField
      label={label}
      type={type}
      value={value}
      onChange={onChange}
      variant="outlined"
      fullWidth={fullWidth}
      margin="normal"
      disabled={nativeProps?.disabled}
      className={nativeProps?.className}
    />
  );
};

export default CustomTextField;

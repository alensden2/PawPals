import React, { ChangeEvent } from 'react';
import TextField from '@material-ui/core/TextField';

interface CustomTextFieldProps {
  label: string;
  type: string;
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}

const CustomTextField: React.FC<CustomTextFieldProps> = ({
  label,
  type,
  value,
  onChange
}) => {
  return (
    <TextField
      label={label}
      type={type}
      value={value}
      onChange={onChange}
      variant="outlined"
      fullWidth
      margin="normal"
    />
  );
};

export default CustomTextField;

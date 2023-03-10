import React from 'react';
import Button from '@material-ui/core/Button';

interface CustomButtonProps {
  disabled?: boolean;
  onClick?: () => void;
  className?: string;
  type?: 'button' | 'submit' | 'reset' | undefined;
  color?: 'inherit' | 'primary' | 'secondary' | 'default' | undefined;
  children?: React.ReactNode;
  variant?: 'text' | 'outlined' | 'contained' | undefined;
}

const CustomButton: React.FC<CustomButtonProps> = ({
  disabled,
  onClick,
  className,
  color,
  children,
  type,
  variant
}) => {
  return (
    <Button
      type={type}
      variant={variant}
      color={color}
      disabled={disabled}
      fullWidth
      className={className}
      onClick={onClick}
    >
      {children}
    </Button>
  );
};

export default CustomButton;

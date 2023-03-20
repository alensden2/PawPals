import React, { useState } from 'react';
import Select, { SelectProps } from '@material-ui/core/Select';
import {
  InputLabel,
  MenuItem,
  OutlinedInput,
  Theme,
  useTheme
} from '@material-ui/core';

interface CustomSelectProps extends SelectProps {
  options: { label?: string; value: string | number }[];
  fullWidth: boolean;
}

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250
    }
  }
};

const CustomSelect: React.FC<CustomSelectProps> = (props) => {
  const theme = useTheme();
  const [selectedOptions, setSelectedOptions] = useState([] as string[]);

  function getStyles(value: string, options: string[], theme: Theme) {
    return {
      fontWeight:
        options.indexOf(value) === -1
          ? theme.typography.fontWeightRegular
          : theme.typography.fontWeightBold
    };
  }

  return (
    <>
      <InputLabel>{props.label}</InputLabel>
      <Select
        labelId={props.labelId || 'select-label-' + Math.random() * 1000}
        id={props.id || 'select-' + Math.random() * 1000}
        value={props.value}
        onChange={(e, c) => {
          setSelectedOptions(e.target.value as string[]);
          if (props.onChange) {
            return props.onChange(e, c);
          }
        }}
        variant="outlined"
        fullWidth={props.fullWidth}
        margin="dense"
        multiple={props.multiple}
        MenuProps={MenuProps}
        className={props.className}
      >
        {props.options.map(
          (option: { label?: string; value: string | number }) => {
            return (
              <MenuItem
                key={option.label || option.value}
                value={option.value}
                style={getStyles(option.value + '', selectedOptions, theme)}
              >
                {option.label || option.value}
              </MenuItem>
            );
          }
        )}
      </Select>
    </>
  );
};

export default CustomSelect;

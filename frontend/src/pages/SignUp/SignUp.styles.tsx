import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginTop: theme.spacing(4),
    marginBottom: theme.spacing(4),
    border: '1px solid #ccc',
    boxShadow: '0.1em 0.1em 0.2em #aaa'
  },
  expandContainer: {
    '-webkit-transition': 'max-height 0.8s',
    '-moz-transition': 'max-height 0.8s',
    '-ms-transition': 'max-height 0.8s',
    '-o-transition': 'max-height 0.8s',
    transition: ' max-height 0.8s',

    '&.expanded.vet': {
      maxHeight: '40em'
    },
    '&.expanded.petowner': {
      maxHeight: '25em'
    },
    '&.collapsed': {
      maxHeight: '0'
    }
  },
  submitButton: {
    marginTop: theme.spacing(4)
  },
  selectDropdown: {
    width: '100%'
  },
  selectDropdownContainer: {
    width: '100%',
    marginTop: theme.spacing(2)
  },
  formContainer: {
    paddingBottom: theme.spacing(2)
  },
  formLabel: {
    marginTop: theme.spacing(14)
  },
  disabledFormField: {
    '& input': {
      backgroundColor: '#eee',
      pointerEvents: 'none',
      cursor: 'not-allowed',
      color: '#666'
    }
  },
  dropdown: {
    marginTop: theme.spacing(1)
  }
}));

export default useStyles;

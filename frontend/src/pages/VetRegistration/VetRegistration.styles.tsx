import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    marginTop: theme.spacing(1)
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
  submitButton: {
    marginTop: theme.spacing(2)
  },
  dropdown: {
    marginTop: theme.spacing(1)
  }
}));

export default useStyles;

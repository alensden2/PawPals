import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginTop: theme.spacing(4),
    marginBottom: theme.spacing(4)
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
  }
}));

export default useStyles;

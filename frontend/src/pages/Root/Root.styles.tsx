import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    height: '100%'
  },
  submitButton: {
    marginTop: theme.spacing(2)
  }
}));

export default useStyles;

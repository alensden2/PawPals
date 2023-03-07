import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: theme.spacing(2),
    border: `1px solid ${theme.palette.grey[300]}`,
    borderRadius: theme.shape.borderRadius
  },
  leftContainer: {
    display: 'flex',
    flex: 1
  },
  rightContainer: {
    display: 'flex',
    flexDirection: 'column'
  },
  image: {
    width: '100%',
    height: '93vh',
    marginRight: theme.spacing(2)
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    border: `1px solid ${theme.palette.grey[300]}`,
    width: 'maxWidth',
    cursor: 'pointer',
    height: '50px',
    marginBottom: '12px',
    padding: '10px'
  },
  pawpalsText: {
    fontFamily: 'Climate Crisis',
    fontSize: '4.5rem'
  },
  loginText: {
    fontFamily: 'Oswald'
  },
  signUpText: {
    fontFamily: 'Oswald'
  }
}));

export default useStyles;

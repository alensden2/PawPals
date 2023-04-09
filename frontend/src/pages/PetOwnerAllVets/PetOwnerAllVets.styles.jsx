import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    paddingTop: '64px',
    flexDirection: 'column',
    height: '100%'
  },
  grid: {
    margin: '0px',
    paddingTop: '24px',
    width: '100%'
  },
  paper: {
    position: 'absolute',
    width: '40vw',
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)'
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: theme.spacing(2)
  },
  button: {
    marginLeft: theme.spacing(1)
  },
  label: {
    'margin-top': '0.5em',
    'font-weight': '400',
    'font-size': '1rem',
    'line-height': '1.4375em'
  }
}));

export default useStyles;

import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    height: '100%'
  },
  grid: {
    margin: '0px',
    width: '100%',
    padding: '32px',
    height: '100%'
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    backgroundColor: '#E6E6E6',
    color: theme.palette.text.secondary
  },
  paperContainerGrid: {
    display: 'flex',
    alignItems: 'center'
  },
  leftContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '90%'
  },
  rightContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    flex: 1,
    height: '100%'
  },
  image: {
    maxWidth: '100%',
    height: 'auto'
  },
  card: {
    width: 500,
    height: 100,
    margin: 10,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    cursor: 'pointer',
    '&:hover': {
      background: '#f1f1f1'
    }
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#333'
  }
}));

export default useStyles;

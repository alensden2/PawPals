import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    paddingTop: '64px',
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
    height: '200px'
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
  icon: {
    color: '#333'
  },
  cardContent: {
    display: 'flex',
    gap: 10,
    alignItems: 'center'
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#333'
  }
}));

export default useStyles;

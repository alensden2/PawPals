import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    maxWidth: 900,
    padding: '24px 0',
    width: '100%'
  },
  card: {
    background: '#f3f3f3'
  },
  petInfoImage: {
    width: '10rem',
    height: '10rem',
    borderRadius: '50%',
    objectFit: 'cover',
    marginRight: theme.spacing(2),
    [theme.breakpoints.down('sm')]: {
      width: '8rem',
      height: '8rem'
    }
  },
  subcomponent: {
    marginBottom: theme.spacing(3),
    padding: theme.spacing(3),
    boxShadow: '0px 2px 10px rgba(0, 0, 0, 0.1)'
  },
  vetSubComponent: {
    padding: theme.spacing(3),
    marginBottom: 0,
    boxShadow: '0px 2px 10px rgba(0, 0, 0, 0.1)'
  },
  title: {
    fontWeight: 'bold',
    color: '#4e4e4e'
  },
  infoHeader: {
    marginTop: theme.spacing(2),
    fontWeight: 'bold',
    color: '#4e4e4e'
  },
  infoText: {
    marginTop: theme.spacing(1),
    color: theme.palette.text.secondary
  },
  healthCard: {
    width: '8rem',
    height: '8rem',
    borderRadius: '50%',
    objectFit: 'cover',
    [theme.breakpoints.down('sm')]: {
      width: '6rem',
      height: '6rem'
    }
  },
  cardContent: {
    paddingBottom: '0px'
  },
  headerContainer: {
    display: 'flex',
    alignItems: 'center'
  },
  headerTitle: {
    fontWeight: 'bold',
    color: '#3679a3',
    textDecorationLine: 'underline'
  }
}));

export default useStyles;

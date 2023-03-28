import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  main: {
    margin: '0px',
    paddingTop: '24px',
    width: '100%'
  },
  petCardContainer: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center'
  },
  root: {
    width: 345,
    marginBottom: theme.spacing(4),
    border: '1px solid #ccc',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)'
  }
}));

export default useStyles;

import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    width: 345,
    marginBottom: theme.spacing(4),
    border: '1px solid #ccc',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)'
  },
  headerContainer: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '16px 16px 0 16px'
  },
  actionButtons: {
    top: theme.spacing(1),
    right: theme.spacing(1),
    display: 'flex',
    justifyContent: 'flex-end'
  },
  media: {
    height: 200
  },
  button: {
    marginLeft: theme.spacing(1)
  }
}));

export default useStyles;

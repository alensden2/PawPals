import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    width: 345,
    marginBottom: theme.spacing(4),
    border: '1px solid #ccc',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)'
  },
  media: {
    height: 200
  },
  button: {}
}));

export default useStyles;

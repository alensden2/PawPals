import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(() => ({
  root: {
    display: 'flex',
    paddingTop: '64px',
    flexDirection: 'column',
    height: '100%'
  },
  container: {
    display: 'flex',
    alignItems: 'center',
    flexDirection: 'column'
  }
}));

export default useStyles;

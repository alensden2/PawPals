import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(() => ({
  root: {
    display: 'flex',
    paddingTop: '64px',
    flexDirection: 'column'
  },
  grid: {
    margin: '0px',
    paddingTop: '24px',
    width: '100%'
  }
}));

export default useStyles;

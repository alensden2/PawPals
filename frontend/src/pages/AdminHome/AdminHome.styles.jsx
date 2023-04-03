import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(() => ({
  root: {
    padding: '64px',
    height: 'calc(100% - 128px)'
  },
  card: {
    cursor: 'pointer',
    width: '310px',
    height: '265px'
  },
  content: {
    display: 'flex',
    alignItems: 'center',
    flexDirection: 'column'
  },
  innerContainer: {
    display: 'flex',
    height: '100%',
    flexDirection: 'row',
    overflowY: 'auto',
    justifyContent: 'center',
    gap: 20
  }
}));

export default useStyles;

import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(() => ({
  container: {
    marginTop: '10px'
  },
  headerRow: {
    background: '#f5f5f5'
  },
  table: {
    minWidth: 650
  },
  button: {
    width: 100
  },
  headerCell: { fontWeight: 'bold' }
}));

export default useStyles;

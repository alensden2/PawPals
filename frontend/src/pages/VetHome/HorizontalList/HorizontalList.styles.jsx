import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    padding: '32px'
  },
  header: {
    display: 'flex',
    background: '#D7DAE5',
    alignItems: 'center',
    paddingLeft: 8
  },
  listTitle: {
    fontFamily: 'Marker Felt !important',
    paddingBottom: 4,
    paddingLeft: 8,
    paddingTop: 4
  },
  divider: {
    margin: '0px !important',
    border: '1px solid black !important'
  },
  media: {
    height: 200
  },
  button: {
    marginLeft: theme.spacing(1)
  },
  horizontalListContainer: {
    display: 'flex',
    gap: '30px',
    overflowX: 'auto',
    paddingTop: 8
  },
  cardContainer: {
    display: 'flex',
    width: '300px'
  },
  icon: {
    fontSize: 36
  }
}));

export default useStyles;

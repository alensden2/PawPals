import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  appBar: {
    backgroundColor: '#0B032D',
    color: '#fff'
  },
  logo: {
    marginRight: theme.spacing(1)
  },
  title: {
    marginLeft: theme.spacing(1)
  },
  grow: {
    flexGrow: 1
  },
  clickable: {
    cursor: 'pointer',
    '&:hover': {
      textDecoration: 'underline'
    }
  }
}));

export default useStyles;

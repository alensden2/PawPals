import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    paddingTop: '64px'
  },
  body: {
    fontFamily: 'Arial, sans-serif',
    margin: 0,
    padding: 0
  },
  header: {
    backgroundColor: '#333',
    color: '#fff',
    textAlign: 'center',
    padding: '1rem'
  },
  main: {
    maxWidth: '800px',
    margin: '0 auto',
    padding: '2rem'
  },
  article: {
    marginBottom: '2rem'
  },
  articleHeading: {
    fontSize: '2rem',
    marginBottom: '1rem'
  },
  image: {
    width: '100px',
    height: 'auto',
    marginRight: '1rem'
  },
  articleContent: {
    display: 'flex',
    alignItems: 'center'
  },
  articleContentImg: {
    maxWidth: '200px',
    marginRight: '1rem'
  },
  articleContentDiv: {
    flex: 1
  },
  p: {
    fontSize: '1.2rem',
    lineHeight: 1.5,
    marginBottom: '1rem'
  },
  readMore: {
    display: 'block',
    textAlign: 'right',
    '& a': {
      color: '#333',
      textDecoration: 'none',
      borderBottom: '1px solid #333'
    }
  }
});

export default useStyles;

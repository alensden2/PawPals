import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  pawPalsTitle: {
    marginLeft: theme.spacing(1)
  },
  header: {
    backgroundColor: '#FFF',
    color: '#333',
    boxShadow: 'none',
    borderBottom: `1px solid ${theme.palette.divider}`
  },
  logo: {
    fontWeight: 'bold',
    marginRight: theme.spacing(2),
    '&:hover': {
      textDecoration: 'none'
    }
  },
  navLinks: {
    flexGrow: 1,
    display: 'flex',
    justifyContent: 'flex-end',
    [theme.breakpoints.down('sm')]: {
      display: 'none'
    }
  },
  navLink: {
    marginLeft: theme.spacing(3),
    display: 'flex',
    alignItems: 'center',
    '&:hover': {
      textDecoration: 'none'
    }
  },
  menuButton: {
    [theme.breakpoints.up('md')]: {
      display: 'none'
    }
  },
  hero: {
    backgroundColor: '#fff',
    padding: theme.spacing(8, 0),
    textAlign: 'center'
  },
  heroTitle: {
    fontSize: 48,
    fontWeight: 'bold',
    marginBottom: theme.spacing(4)
  },
  heroSubtitle: {
    fontSize: 24,
    marginBottom: theme.spacing(4)
  },
  heroButton: {
    margin: theme.spacing(2)
  },
  care: {
    backgroundColor: '#F8F8F8',
    padding: theme.spacing(8, 0),
    textAlign: 'center'
  },
  careTitle: {
    fontSize: 36,
    color: '#4C4C4C',
    marginBottom: theme.spacing(4)
  },
  careSubtitle: {
    fontSize: 20,
    lineHeight: 1.5,
    color: '#7F7F7F'
  },
  petBackgroundMain: {
    backgroundImage:
      'url("https://images.pexels.com/photos/2253275/pexels-photo-2253275.jpeg")',
    backgroundSize: 'cover',
    backgroundPosition: 'center center',
    height: 600,
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
    color: '#fff',
    padding: theme.spacing(8, 0)
  },
  petBackground: {
    backgroundImage:
      'url("https://images.unsplash.com/photo-1543164888-462c8603cbd9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80")',
    backgroundSize: 'cover',
    backgroundPosition: 'center center',
    height: 600,
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
    color: '#fff',
    padding: theme.spacing(8, 0)
  },
  petBackgroundTitle: {
    fontSize: 80,
    fontWeight: 'bold',
    marginBottom: theme.spacing(4)
  },
  petBackgroundSubtitle: {
    fontSize: 24,
    marginBottom: theme.spacing(4)
  },
  bottomImageTitle: {
    fontSize: 40,
    fontWeight: 'bold',
    marginBottom: theme.spacing(4)
  },
  bottomImageSubtitle: {
    fontSize: 24,
    marginBottom: theme.spacing(4)
  },
  containerClass: {
    paddingTop: 64,
    paddingLeft: 0,
    maxWidth: '100%',
    margin: 0,
    paddingRight: 0
  },
  footer: {
    backgroundColor: '#F8F8F8',
    padding: theme.spacing(4),
    marginTop: theme.spacing(10),
    textAlign: 'center'
  },
  footerLink: {
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2)
  },
  headerTitleContainer: {
    display: 'flex',
    flexDirection: 'row'
  },
  gridContainer: {
    width: '100%',
    margin: 0
  }
}));

export default useStyles;

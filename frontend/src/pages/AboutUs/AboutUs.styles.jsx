/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { makeStyles } from '@material-ui/core/styles';

export const AboutUsStyles = makeStyles((theme) => ({
  header: {
    backgroundColor: '#FFF',
    color: '#333',
    boxShadow: 'none',
    borderBottom: `1px solid ${theme.palette.divider}`
  },
  headerTitleContainer: {
    display: 'flex',
    flexDirection: 'row'
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
      textDecoration: 'none',
      cursor: 'pointer'
    }
  },
  '*': {
    boxSizing: 'border-box'
  },
  a: {
    color: '#555',
    textDecoration: 'none',
    '&:hover': {
      color: '#333'
    }
  },
  button: {
    marginRight: '10px',
    padding: '10px',
    backgroundColor: 'green',
    color: '#fff',
    borderRadius: '5px',
    transition: 'background-color 0.3s ease',
    '&:hover': {
      backgroundColor: '#2c7a78'
    }
  },
  nav: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: '20px',
    backgroundColor: '#fff',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)'
  },
  hero: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    height: '50vh',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    color: '#fff',
    textAlign: 'center'
  },
  aboutusHero: {
    backgroundImage:
      "url('https://img.freepik.com/free-photo/group-portrait-adorable-puppies_53876-64778.jpg?w=1380&t=st=1679203551~exp=1679204151~hmac=003c666893d7d93076ace62c9804b429bb75b6c31f0bb336dc87cb818609d5e1')"
  },
  aboutUs: {
    padding: '2rem',
    backgroundColor: '#f8f8f8',
    color: '#333',
    textAlign: 'center'
  },
  aboutUsTitle: {
    fontSize: '2rem',
    marginBottom: '1rem'
  },
  aboutUsContent: {
    fontSize: '1.2rem',
    lineHeight: 1.5,
    marginBottom: '1rem'
  },
  footer: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '1rem',
    backgroundColor: '#333',
    color: '#fff',
    fontSize: '1.2rem'
  }
}));

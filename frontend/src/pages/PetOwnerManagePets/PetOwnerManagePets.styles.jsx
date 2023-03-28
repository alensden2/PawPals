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
  },
  addPetRow: {
    display: 'flex',
    justifyContent: 'flex-end',
    paddingTop: '24px'
  },
  addPetButton: {
    width: '240px',
    marginLeft: '100px'
  },
  buttonContainer: {
    display: 'flex',
    alignItems: 'center',
    width: '100%',
    justifyContent: 'center'
  },
  emptyDivContainer: {
    width: '50%'
  }
}));

export default useStyles;

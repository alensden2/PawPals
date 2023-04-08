import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center'
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
    width: '80%',
    height: '80%',
    display: 'flex',
    flexDirection: 'column'
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: '1rem',
    border: `1px solid ${theme.palette.divider}`,
    background: '#F6F6F6',
    padding: '20px'
  },
  sidebar: {
    display: 'flex',
    flexDirection: 'column',
    minWidth: '200px'
  },
  box: {
    height: '100%'
  },
  tabPanel: {
    flexGrow: 1,
    borderLeft: `1px solid ${theme.palette.divider}`
  },
  tab: {
    '&.Mui-selected': {}
  },
  modalHeader: {},
  tabPanelInnerContainer: {
    padding: '20px'
  },
  medicalRecordContainer: {
    height: '500px',
    overflowY: 'auto',
    padding: '8px'
  },
  petDetailsContainer: {
    display: 'flex',
    flexDirection: 'column',
    gap: 20
  },
  media: {
    width: 300,
    height: 200
  },
  medicalRecordCard: {
    paddingBottom: '60px'
  }
}));

export default useStyles;

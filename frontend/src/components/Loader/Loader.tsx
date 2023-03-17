import { CircularProgress, Typography } from '@material-ui/core';
import useStyles from './Loader.styles';

function Loader() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <CircularProgress />
      <Typography
        variant="subtitle1"
        component="h2"
        className={classes.loadingText}
      >
        Loading...
      </Typography>
    </div>
  );
}

export default Loader;

import { Box, Typography } from '@material-ui/core';
import NOTHING_HERE_YET from '@src/assets/images/nothing_here_yet.png';
import useStyles from './EmptyState.styles';

function EmptyState({ text = 'Nothing here yet!' } = {}) {
  const classes = useStyles();

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      mt={8}
      className={classes.boxContainer}
    >
      <img
        src={NOTHING_HERE_YET}
        alt="Empty state icon"
        width={560}
        height={500}
      />
      <Typography
        variant="h5"
        align="center"
        color="textSecondary"
        gutterBottom
      >
        {text}
      </Typography>
    </Box>
  );
}

export default EmptyState;

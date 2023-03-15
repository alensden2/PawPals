import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from '@material-ui/core';

type DeleteDialogProps = {
  isOpen: boolean;
  onCloseDialogClick: () => void;
  onDeleteDialogClick: () => void;
};

const DeleteDialog = ({
  isOpen,
  onCloseDialogClick,
  onDeleteDialogClick
}: DeleteDialogProps) => {
  const handleClose = () => {
    onCloseDialogClick();
  };

  const handleDelete = () => {
    onDeleteDialogClick();
    onCloseDialogClick();
  };

  return (
    <Dialog open={isOpen} onClose={handleClose}>
      <DialogTitle>Confirm Deletion</DialogTitle>
      <DialogContent>
        <DialogContentText>Are you sure you want to delete?</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleDelete} color="secondary">
          Delete
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DeleteDialog;

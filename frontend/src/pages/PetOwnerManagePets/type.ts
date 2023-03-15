import { NullablePet } from '@src/types';

// Define the PetCardProps type
export type PetCardProps = {
  pet: NullablePet;
  onEditClick: (pet: NullablePet) => void;
  onDeleteClick: (pet: NullablePet) => void;
};

// Define the PetListProps type
export type PetListProps = {
  pets: NullablePet[];
  onEditClick: (pet: NullablePet) => void;
  onDeleteClick: (pet: NullablePet) => void;
};

// Define the ModalMode type
export type ModalMode = 'edit' | 'add';

// Define the PetModalState type
export type PetModalState = {
  data: NullablePet;
  isOpen: boolean;
  modalMode: ModalMode;
};

// Define the DeleteDialogState type
export type DeleteDialogState = {
  isOpen: boolean;
  data: NullablePet;
};

// Define the PetsState type
export type PetsState = {
  pets: NullablePet[];
};

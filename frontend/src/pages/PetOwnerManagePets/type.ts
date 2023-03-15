// Define the Pet type
export type Pet = {
  id: number | null;
  age: number | null;
  gender: string | null;
  name: string | null;
  photoUrl: string | null;
  type: string | null;
};

// Define the PetCardProps type
export type PetCardProps = {
  pet: Pet;
  onEditClick: (pet: Pet) => void;
  onDeleteClick: (pet: Pet) => void;
};

// Define the PetListProps type
export type PetListProps = {
  pets: Pet[];
  onEditClick: (pet: Pet) => void;
  onDeleteClick: (pet: Pet) => void;
};

// Define the ModalMode type
export type ModalMode = 'edit' | 'add';

// Define the PetModalState type
export type PetModalState = {
  data: Pet;
  isOpen: boolean;
  modalMode: ModalMode;
};

// Define the DeleteDialogState type
export type DeleteDialogState = {
  isOpen: boolean;
  data: Pet;
};

// Define the PetsState type
export type PetsState = {
  pets: Pet[];
};

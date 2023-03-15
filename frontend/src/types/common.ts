// Allows null values
export type NullablePet = {
  id: number | null;
  age: number | null;
  gender: string | null;
  name: string | null;
  photoUrl: string | null;
  type: string | null;
};

// Does not allow null values
export type Pet = {
  id: number | null;
  age: number;
  gender: string;
  name: string;
  photoUrl: string;
  type: string;
};

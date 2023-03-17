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

export type Vet = {
  userName: string;
  firstName: string;
  lastName: string;
  phoneNo: string;
  email: string;
  experience: number;
  licenseNumber: string;
  qualification: string;
  clinicAddress: string;
  clinicPhotoUrl?: string;
};

export type VetList = {
  vets: Vet[];
};

export type VetsState = {
  vets: Vet[];
};

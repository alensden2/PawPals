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
  userName?: string;
  firstName: string;
  lastName: string;
  phoneNo: string;
  email?: string;
  experience: number;
  licenseNumber: string;
  qualification: string;
  clinicAddress: string;
  clinicPhoto?: File;
};

export type PetOwner = {
  userName?: string;
  firstName: string;
  lastName: string;
  phoneNo: string;
  email?: string;
  address:string;
  photoUrl?: File;
  pets?: Array<Pet>;
};

export type VetList = {
  vets: Vet[];
};

export type VetsState = {
  vets: Vet[];
};

export type MedicalRecord = {
  medicalHistoryId: number;
  dateDiagnosed: string;
  prescription: string;
  vaccines: string;
  pet: {
    id: number;
    age: number;
    gender: string;
    name: string;
    type: string;
  };
  vet: {
    vetId: number;
    name: string;
    phoneNo: string;
    email: string;
    experience: number;
    licenseNumber: string;
    qualification: string;
    clinicAddress: string;
    clinicPhotoUrl?: string;
  };
};

export type MedicalRecordList = {
  medicalRecords: MedicalRecord[];
};

export type MedicalRecordState = {
  medicalRecords: MedicalRecord[];
};

export type MedicalRecordCardProps = {
  medicalRecord: MedicalRecord;
};

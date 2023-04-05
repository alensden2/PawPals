import APPROVE_REJECT_IMAGE from '@src/assets/images/admin_approve_reject.png';
import VETS_IMAGE from '@src/assets/images/admin_vets.jpg';
import PET_OWNER_IMAGE from '@src/assets/images/admin_pet_owners.jpg';

export const ROLE_TO_ROUTE_MAPPING = {
  ROLE_VET: '/vet',
  VET: '/vet',
  ROLE_PET_OWNER: '/pet-owner',
  PET_OWNER: '/pet-owner',
  ROLE_ADMIN: '/admin',
  NONE: ''
};

export const PET_OWNER_HOME_PAGE_CARDS = [
  {
    uid: 'MANAGE_PETS',
    title: 'Manage Pets',
    color: '#5F758E',
    route: '/pet-owner/manage-pets'
  },
  {
    uid: 'MEDICAL_HISTORY',
    title: 'Medical History',
    color: '#4d7c8a',
    route: '/pet-owner/medical-record'
  },
  {
    uid: 'BOOK_APPOINTMENT_WITH_VET',
    title: 'Book appointment with Vet',
    color: '#7f9c96',
    route: '/pet-owner/all-vets'
  },
  {
    uid: 'HEALTH_AND_DISEASE',
    title: 'Learn about Pet health and diseases',
    color: '#8fad88',
    route: '/pet-owner/pet-health-and-diseases'
  },
  {
    uid: 'SUPPORT',
    title: 'Support/ Contact Us',
    color: '#cbdf90',
    route: '/pet-owner/pet-health-and-diseases'
  }
];

export const PLACEHOLDER_PET_IMAGE =
  'https://via.placeholder.com/150x150.png?text=Pet+Image';

export const PLACEHOLDER_VET_CLINIC_IMAGE =
  'https://via.placeholder.com/500x500.png?text=Clinic+Image';

export const PET_NAME = 'PET_NAME';

export const VET_HOME_APPOINTMENT_CONSTANT = {
  PENDING: {
    headerText: 'Review Appointments',
    shouldShowApproveDeclineButtons: true,
    shouldShowDiagnoseButton: false,
    shouldShowViewDetailsButton: false,
    shouldShowInfoButton: true
  },
  CONFIRMED: {
    headerText: 'Upcoming Appointments',
    shouldShowApproveDeclineButtons: false,
    shouldShowDiagnoseButton: true,
    shouldShowViewDetailsButton: false,
    shouldShowInfoButton: true
  },
  COMPLETED: {
    headerText: 'Completed Appointments',
    shouldShowApproveDeclineButtons: false,
    shouldShowDiagnoseButton: false,
    shouldShowViewDetailsButton: true,
    shouldShowInfoButton: false
  }
};

export const QUALIFICATION_OPTIONS: Array<{ value: string }> = [
  {
    value: 'MBBS'
  },
  {
    value: 'BMBS'
  },
  {
    value: 'MD'
  },
  {
    value: 'DO'
  }
];

export const ADMIN_HOME_PAGE_CARDS = [
  {
    key: 1,
    image: APPROVE_REJECT_IMAGE,
    altImage: '"Manage Vets',
    title: 'Manage Vets',
    description: 'Approve or reject veterinarian.',
    route: '/admin/manage-vets'
  },
  {
    key: 2,
    image: VETS_IMAGE,
    altImage: 'All Vets',
    title: 'All Vets',
    description: 'Show a list of all veterinarians',
    route: '/admin/all-vets'
  },
  {
    key: 3,
    image: PET_OWNER_IMAGE,
    altImage: 'All Pet Owners',
    title: 'All Pet Owners',
    description: 'Display a list of all pet owners',
    route: '/admin/all-pet-owners'
  }
];

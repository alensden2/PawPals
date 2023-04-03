export const ROLE_TO_ROUTE_MAPPING = {
  ROLE_VET: '/vet',
  ROLE_PET_OWNER: '/pet-owner',
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
  DIAGNOSED: {
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

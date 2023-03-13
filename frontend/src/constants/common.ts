export const ROLE_TO_ROUTE_MAPPING = {
  VET: '/vet',
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
    route: '/pet-owner/medical-history'
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

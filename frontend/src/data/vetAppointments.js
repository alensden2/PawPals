export const vetAppointments = {
  allAppointments: [
    {
      appointment: {
        id: 1,
        date: '30-03-2023',
        startTime: '14:30',
        endTime: '15:00',
        status: 'CONFIRMED',
        // reason: 'Annual checkup',
        location: '1234 Elm Street, Anytown USA'
        // notes: 'Please bring a stool sample for testing'
      },
      pet: {
        id: 1,
        age: 2,
        gender: 'Female',
        name: 'Bella',
        photoUrl:
          'https://images.pexels.com/photos/2686914/pexels-photo-2686914.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
        type: 'Cat'
      },
      petOwner: {
        id: 1,
        firstName: 'Leslie',
        lastName: 'Smith',
        phoneNo: '+1839170202',
        photoUrl: 'https://example.com/images/leslie-smith.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 1,
          dateDiagnosed: '2022-01-15',
          prescription: 'Ibuprofen',
          vaccines: 'Flu, COVID-19',
          pet: {
            id: 1,
            age: 2,
            gender: 'Female',
            name: 'Bella',
            type: 'Cat'
          },
          vet: {
            vetId: 1,
            name: 'John Doe',
            phoneNo: '123-456-7890',
            email: 'johndoe@gmail.com',
            experience: 5,
            licenseNumber: '12345',
            qualification: 'DVM',
            clinicAddress: '456 Main St, Anytown USA'
          }
        },
        {
          medicalHistoryId: 2,
          dateDiagnosed: '2022-03-05',
          prescription: 'Antibiotics',
          vaccines: 'Rabies, Feline leukemia',
          pet: {
            id: 1,
            age: 2,
            gender: 'Female',
            name: 'Bella',
            type: 'Cat'
          },
          vet: {
            vetId: 2,
            name: 'Jane Smith',
            phoneNo: '555-555-5555',
            email: 'janesmith@gmail.com',
            experience: 10,
            licenseNumber: '67890',
            qualification: 'DVM',
            clinicAddress: '789 Oak St, Anytown USA'
          }
        }
      ]
    },
    {
      appointment: {
        id: 2,
        date: '05-04-2023',
        startTime: '10:00',
        endTime: '10:30',
        status: 'CONFIRMED'
      },
      pet: {
        id: 2,
        age: 4,
        gender: 'Male',
        name: 'Max',
        photoUrl:
          'https://images.unsplash.com/photo-1583511655826-05700d52f4d9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=776&q=80',
        type: 'Dog'
      },
      petOwner: {
        id: 2,
        firstName: 'Emma',
        lastName: 'Williams',
        phoneNo: '+1902374639',
        photoUrl: 'https://example.com/images/emma-williams.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 3,
          dateDiagnosed: '2022-06-10',
          prescription: 'Antibiotics',
          vaccines: 'Rabies',
          pet: {
            id: 2,
            age: 4,
            gender: 'Male',
            name: 'Max',
            type: 'Dog'
          },
          vet: {
            vetId: 2,
            name: 'Sarah Johnson',
            phoneNo: '987-654-3210',
            email: 'sjohnson@gmail.com',
            experience: 8,
            licenseNumber: '67890',
            qualification: 'DVM',
            clinicAddress: '456 Elm St, Anytown USA'
          }
        },
        {
          medicalHistoryId: 4,
          dateDiagnosed: '2022-06-10',
          prescription: 'Antibiotics',
          vaccines: 'Rabies',
          pet: {
            id: 2,
            age: 4,
            gender: 'Male',
            name: 'Max',
            type: 'Dog'
          },
          vet: {
            vetId: 2,
            name: 'Sarah Johnson',
            phoneNo: '987-654-3210',
            email: 'sjohnson@gmail.com',
            experience: 8,
            licenseNumber: '67890',
            qualification: 'DVM',
            clinicAddress: '456 Elm St, Anytown USA'
          }
        }
      ]
    },
    {
      appointment: {
        id: 3,
        date: '02-04-2023',
        startTime: '10:00',
        endTime: '11:00',
        status: 'DIAGNOSED'
      },
      pet: {
        id: 3,
        age: 4,
        gender: 'Male',
        name: 'Buddy',
        photoUrl:
          'https://images.unsplash.com/photo-1537151608828-ea2b11777ee8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=994&q=80',
        type: 'Dog'
      },
      petOwner: {
        id: 3,
        firstName: 'Emily',
        lastName: 'Johnson',
        phoneNo: '+1234567890',
        photoUrl: 'https://example.com/images/emily-johnson.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 5,
          dateDiagnosed: '2022-12-01',
          prescription: 'Antibiotics',
          vaccines: 'Rabies',
          pet: {
            id: 3,
            age: 4,
            gender: 'Male',
            name: 'Buddy',
            type: 'Dog'
          },
          vet: {
            vetId: 3,
            name: 'Jane Smith',
            phoneNo: '555-555-5555',
            email: 'janesmith@gmail.com',
            experience: 10,
            licenseNumber: '54321',
            qualification: 'DVM',
            clinicAddress: '456 Park Ave, Anytown USA'
          }
        },
        {
          medicalHistoryId: 6,
          dateDiagnosed: '2022-12-01',
          prescription: 'Antibiotics',
          vaccines: 'Rabies',
          pet: {
            id: 3,
            age: 4,
            gender: 'Male',
            name: 'Buddy',
            type: 'Dog'
          },
          vet: {
            vetId: 3,
            name: 'Jane Smith',
            phoneNo: '555-555-5555',
            email: 'janesmith@gmail.com',
            experience: 10,
            licenseNumber: '54321',
            qualification: 'DVM',
            clinicAddress: '456 Park Ave, Anytown USA'
          }
        }
      ]
    },
    {
      appointment: {
        id: 4,
        date: '04-05-2023',
        startTime: '10:00',
        endTime: '11:00',
        status: 'DIAGNOSED'
      },
      pet: {
        id: 4,
        age: 5,
        gender: 'Male',
        name: 'Charlie',
        photoUrl:
          'https://images.unsplash.com/photo-1620668004589-a97feabdb45e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1774&q=80',
        type: 'Dog'
      },
      petOwner: {
        id: 4,
        firstName: 'Karen',
        lastName: 'Jones',
        phoneNo: '+1987654321',
        photoUrl: 'https://example.com/images/karen-jones.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 7,
          dateDiagnosed: '2023-03-01',
          prescription: 'Antibiotics',
          vaccines: 'Rabies, Parvo',
          pet: {
            id: 4,
            age: 5,
            gender: 'Male',
            name: 'Charlie',
            type: 'Dog'
          },
          vet: {
            vetId: 2,
            name: 'Jane Smith',
            phoneNo: '555-555-1212',
            email: 'jane.smith@vetclinic.com',
            experience: 10,
            licenseNumber: '54321',
            qualification: 'DVM',
            clinicAddress: '456 Elm St, Anytown USA'
          }
        },
        {
          medicalHistoryId: 8,
          dateDiagnosed: '2022-06-15',
          prescription: 'Pain reliever',
          vaccines: 'Distemper, Lyme',
          pet: {
            id: 4,
            age: 5,
            gender: 'Male',
            name: 'Charlie',
            type: 'Dog'
          },
          vet: {
            vetId: 3,
            name: 'Robert Lee',
            phoneNo: '555-555-2323',
            email: 'robert.lee@vetclinic.com',
            experience: 15,
            licenseNumber: '98765',
            qualification: 'DVM',
            clinicAddress: '789 Oak St, Anytown USA'
          }
        }
      ]
    },
    {
      appointment: {
        id: 5,
        date: '05-04-2023',
        startTime: '11:00',
        endTime: '12:00',
        status: 'PENDING'
      },
      pet: {
        id: 5,
        age: 5,
        gender: 'Male',
        name: 'Daisy',
        photoUrl:
          'https://images.unsplash.com/photo-1527778676396-eceba283ddfa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80',
        type: 'Dog'
      },
      petOwner: {
        id: 5,
        firstName: 'Emma',
        lastName: 'Jones',
        phoneNo: '+14155552075',
        photoUrl: 'https://example.com/images/emma-jones.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 9,
          dateDiagnosed: '2022-07-20',
          prescription: 'Antibiotics',
          vaccines: 'Rabies',
          pet: {
            id: 2,
            age: 5,
            gender: 'Male',
            name: 'Daisy',
            type: 'Dog'
          },
          vet: {
            vetId: 2,
            name: 'Lisa Brown',
            phoneNo: '123-456-7890',
            email: 'lisabrown@gmail.com',
            experience: 8,
            licenseNumber: '23456',
            qualification: 'DVM',
            clinicAddress: '456 Maple St, Anytown USA'
          }
        }
      ]
    },
    {
      appointment: {
        id: 6,
        date: '30-03-2023',
        startTime: '14:30',
        endTime: '15:00',
        status: 'PENDING'
      },
      pet: {
        id: 6,
        age: 2,
        gender: 'Female',
        name: 'Luna',
        photoUrl:
          'https://images.unsplash.com/photo-1599709606362-2078844247fc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80',
        type: 'Cat'
      },
      petOwner: {
        id: 6,
        firstName: 'Leslie',
        lastName: 'Smith',
        phoneNo: '+1839170202',
        photoUrl: 'https://example.com/images/john-smith.jpg'
      },
      petMedicalRecord: [
        {
          medicalHistoryId: 10,
          dateDiagnosed: '2022-01-15',
          prescription: 'Ibuprofen',
          vaccines: 'Flu, COVID-19',
          pet: {
            id: 6,
            age: 2,
            gender: 'Female',
            name: 'Luna',
            type: 'Cat'
          },
          vet: {
            vetId: 1,
            name: 'John Doe',
            phoneNo: '123-456-7890',
            email: 'johndoe@gmail.com',
            experience: 5,
            licenseNumber: '12345',
            qualification: 'MD',
            clinicAddress: '123 Main St, Anytown USA'
          }
        },
        {
          medicalHistoryId: 11,
          dateDiagnosed: '2022-01-15',
          prescription: 'Ibuprofen',
          vaccines: 'Flu, COVID-19',
          pet: {
            id: 6,
            age: 2,
            gender: 'Female',
            name: 'Luna',
            type: 'Cat'
          },
          vet: {
            vetId: 1,
            name: 'John Doe',
            phoneNo: '123-456-7890',
            email: 'johndoe@gmail.com',
            experience: 5,
            licenseNumber: '12345',
            qualification: 'MD',
            clinicAddress: '123 Main St, Anytown USA'
          }
        }
      ]
    }
  ]
};

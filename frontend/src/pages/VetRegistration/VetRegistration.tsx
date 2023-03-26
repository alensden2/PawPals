import { HeaderContext } from '@src/context';
import React, { useContext, useEffect, useState } from 'react';
import useStyles from './VetRegistration.styles';
import { TextField, Select } from '@src/components';
import { Vet } from '@src/types';

interface VetComponent {
  onVetChange: (vet: Vet) => void;
}

const VetRegistration: React.FC<VetComponent> = (props) => {
  const { setHeader } = useContext(HeaderContext);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [clinicAddress, setClinicAddress] = useState('');
  const [experience, setExperience] = useState(0);
  const [qualifications, setQualifications] = useState([]);
  const [lNumber, setLNumber] = useState('');
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [vetLocal, setVet] = useState({});

  const classes = useStyles();

  const qualificationOptions = [
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

  const selfOnChange = (fieldName: string, value: any) => {
    switch (fieldName) {
      case 'fName':
        setFirstName(value);
        setVet((prevVet: Vet) => {
          const newVetVal: Vet = {
            ...prevVet,
            firstName: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'lName':
        setLastName(value);
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            lastName: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'pNum':
        setPhoneNumber(value);
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            phoneNumber: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'cAddress':
        setClinicAddress(value);
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            clinicAddress: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'yoE':
        setExperience(value);
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            experience: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'lNum':
        setLNumber(value);
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            licenseNumber: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        break;
      case 'qlf':
        setVet((prevVet: Vet) => {
          const newVetVal = {
            ...prevVet,
            qualification: value
          };
          props.onVetChange(newVetVal);
          return newVetVal;
        });
        setQualifications(value);
        break;
    }
  };

  useEffect(() => {
    setHeader({
      shouldShowHeader: false,
      title: 'Vet Registration',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

  return (
    <div>
      <TextField
        label="First Name"
        type="text"
        value={firstName}
        onChange={(event) => selfOnChange('fName', event.target.value)}
        fullWidth={true}
      />
      <TextField
        label="Last Name"
        type="text"
        value={lastName}
        onChange={(event) => selfOnChange('lName', event.target.value)}
        fullWidth={true}
      />
      <TextField
        label="Phone Number"
        type="phoneNum"
        value={phoneNumber}
        onChange={(event) => selfOnChange('pNum', event.target.value)}
        fullWidth={true}
      />
      <TextField
        label="Clinic Address"
        type="text"
        value={clinicAddress}
        onChange={(event) => selfOnChange('cAddress', event.target.value)}
        fullWidth={true}
      />
      <TextField
        label="Years of Experience"
        type="number"
        value={experience + ''}
        onChange={(event) => selfOnChange('yoE', parseInt(event.target.value))}
        fullWidth={true}
      />
      <TextField
        label="License Number"
        type="text"
        value={lNumber}
        onChange={(event) => selfOnChange('lNum', event.target.value)}
        fullWidth={true}
      />
      {/* dropdown */}
      <Select
        label="Qualification"
        type="text"
        value={qualifications}
        onChange={(event) => {
          return selfOnChange('qlf', (event.target as any).value);
        }}
        fullWidth={true}
        options={qualificationOptions}
        multiple={true}
        className={classes.dropdown}
      />
    </div>
  );
};

export default VetRegistration;

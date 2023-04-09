// @ts-nocheck
// react
import React, { useContext, useEffect, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';

// material ui
import {
  Container,
  IconButton,
  InputLabel,
  Link,
  MenuItem,
  Select,
  TextField as MuiTextField,
  Typography
} from '@material-ui/core';
import dayjs from 'dayjs';
import FormControlLabel from '@mui/material/FormControlLabel';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';

import Checkbox from '@mui/material/Checkbox';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { QUALIFICATION_OPTIONS } from '@src/constants/common';
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';

// components
import { Button, TextField, Select as CustomSelect } from '@src/components';

// styles
import useStyles from './SignUp.styles';

// constants
import {
  TOAST_MESSAGE_SIGNUP_ERROR,
  TOAST_MESSAGE_SIGNUP_SUCCESS
} from '@src/constants';

// context
import { ToastContext, HeaderContext } from '@src/context';

// api
import { registerUser } from '@src/api/auth';
import { RegisterUserType } from '@src/api/type';
import { registerVet, postAvailability } from '@src/api/vet';
import { registerPetOwner } from '@src/api/petowner';
import { authenticateUser } from '@src/api';
import { localStorageUtil } from '@src/utils';

// hooks
import { useNavigate } from '@src/hooks';

const setUserInLocalStorage = ({
  userName,
  jwtToken,
  role
}: LocalStorageSetInput): void => {
  localStorageUtil.setItem('user', {
    userName,
    jwtToken,
    role
  });
};

const SignUp: React.FC = () => {
  // styles
  const classes = useStyles();

  // state
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [clinicAddress, setClinicAddress] = useState('');
  const [experience, setExperience] = useState(0);
  const [qualifications, setQualifications] = useState([]);
  const [lNumber, setLNumber] = useState('');
  const [clinicPhoto, setClinicPhoto] = useState(null as unknown as File);
  const [profilePhoto, setProfilePhoto] = useState(null as unknown as File);
  const [loader, setLoader] = useState(false);
  const [defaultAvl, setDefaultAvl] = useState(false);
  const [availability, setAvailability] = useState({
    MONDAY: { start: '', end: '' },
    TUESDAY: { start: '', end: '' },
    WEDNESDAY: { start: '', end: '' },
    THURSDAY: { start: '', end: '' },
    FRIDAY: { start: '', end: '' },
    SATURDAY: { start: '', end: '' },
    SUNDAY: { start: '', end: '' }
  });

  const { setHeader } = useContext(HeaderContext);

  // useEffect
  useEffect(() => {
    setHeader({
      shouldShowHeader: false
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleClose = () => {
    setLoader(false);
  };

  // context
  const { setToast } = useContext(ToastContext);

  // navigation
  const navigate = useNavigate();

  // submit function
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setLoader(true);
    //Step 1: register user
    //step 2: register vet/ pet-owner
    const response: RegisterUserType = await registerUser({
      userName,
      password,
      role: selectedOption,
      email
    });

    const hasError = response.error;
    if (hasError) {
      // display error toast
      setToast({ type: 'error', message: response.errorMessage });
      setLoader(false);
    } else {
      const response: AuthenticateUserType = await authenticateUser({
        username: userName,
        password
      });
      const uName = response.userName;
      const jwtToken = response.jwtToken;
      const role = response.role;

      setUserInLocalStorage({
        userName: uName,
        jwtToken,
        role
      });

      let success = false;
      if (selectedOption === 'VET') {
        const response = await registerVet({
          userName,
          firstName,
          lastName,
          clinicAddress,
          experience,
          licenseNumber: lNumber,
          phoneNo: phoneNumber,
          qualification: qualifications.join(', '),
          clinicPhoto,
          profileStatus: 'PENDING',
          email
        });
        success = response === userName;
        //post vet availability
        if (success) {
          const vetAvailabilityObj = [];
          if (availability) {
            Object.entries(availability).forEach(([dayOfWeek, avl]) => {
              const avlObj = {
                dayOfWeek,
                vetUserId: userName,
                slots: [
                  {
                    first: dayjs(avl.start).format('HH:mm'),
                    second: dayjs(avl.end).format('HH:mm')
                  }
                ]
              };
              vetAvailabilityObj.push(avlObj);
            });
          }
          const response = await postAvailability(vetAvailabilityObj);
          success = response.data.success;
        }
      } else if (selectedOption === 'PET_OWNER') {
        const response = await registerPetOwner({
          userName,
          firstName,
          lastName,
          address: clinicAddress,
          phoneNo: phoneNumber,
          photoUrl: profilePhoto,
          email
        });
        success = !!response.success && !response.error;
      }
      setLoader(false);
      // display success toast and ask user to sign in
      setToast({
        type: success ? 'success' : 'error',
        message: success
          ? TOAST_MESSAGE_SIGNUP_SUCCESS
          : TOAST_MESSAGE_SIGNUP_ERROR
      });

      localStorageUtil.clear();

      if (success) {
        navigate('/signin');
      }
    }
  };

  useEffect(() => {
    handleVetAvlChange();
  }, [defaultAvl]);

  const handleVetAvlChange = (event, type) => {
    const finalUpdatedValue = JSON.parse(JSON.stringify(availability));
    if (event) {
      finalUpdatedValue['MONDAY'] = {
        ...finalUpdatedValue['MONDAY'],
        [type]: event
      };
    }
    if (defaultAvl) {
      finalUpdatedValue['TUESDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
      finalUpdatedValue['WEDNESDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
      finalUpdatedValue['THURSDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
      finalUpdatedValue['FRIDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
      finalUpdatedValue['SATURDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
      finalUpdatedValue['SUNDAY'] = {
        start: finalUpdatedValue['MONDAY'].start,
        end: finalUpdatedValue['MONDAY'].end
      };
    }
    setAvailability(finalUpdatedValue);
  };

  const handleSelectChange = (event: any) => {
    setSelectedOption(event.target.value);
  };

  const onBackClick = () => {
    navigate('/');
  };

  return (
    <div>
      <div onClick={onBackClick}>
        <IconButton>
          <ArrowBackIosIcon className="back-button-icon" />
        </IconButton>
        <Button color="inherit">Paw pals</Button>
      </div>
      <Container maxWidth="xs" className={classes.root}>
        <Backdrop
          sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={loader}
          onClick={handleClose}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
        <Typography variant="h4" align="center" gutterBottom>
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit} className={classes.formContainer}>
          <TextField
            label="Username"
            type="text"
            value={userName}
            fullWidth={true}
            onChange={(event) => setUserName(event.target.value)}
          />
          <TextField
            label="Email"
            type="email"
            value={email}
            fullWidth={true}
            onChange={(event) => setEmail(event.target.value)}
          />
          <TextField
            label="Password"
            type="password"
            value={password}
            fullWidth={true}
            onChange={(event) => setPassword(event.target.value)}
          />
          <div className={classes.selectDropdownContainer}>
            <InputLabel>Select Role</InputLabel>
            <Select
              value={selectedOption}
              onChange={handleSelectChange}
              className={classes.selectDropdown}
            >
              <MenuItem value="VET">Vet</MenuItem>
              <MenuItem value="PET_OWNER">Pet Owner</MenuItem>
            </Select>
          </div>
          <div
            className={[
              classes.expandContainer,
              selectedOption ? 'expanded' : 'collapsed',
              selectedOption && selectedOption === 'VET' ? 'vet' : 'petowner'
            ].join(' ')}
          >
            {selectedOption ? (
              <>
                <TextField
                  label="First Name"
                  type="text"
                  value={firstName}
                  onChange={(event) => setFirstName(event.target.value)}
                  fullWidth={true}
                />
                <TextField
                  label="Last Name"
                  type="text"
                  value={lastName}
                  onChange={(event) => setLastName(event.target.value)}
                  fullWidth={true}
                />
                <TextField
                  label="Phone Number"
                  type="phoneNum"
                  value={phoneNumber}
                  onChange={(event) => setPhoneNumber(event.target.value)}
                  fullWidth={true}
                />
              </>
            ) : (
              <></>
            )}
            {selectedOption === 'VET' ? (
              <>
                <div>
                  <TextField
                    label="Clinic Address"
                    type="text"
                    value={clinicAddress}
                    onChange={(event) => setClinicAddress(event.target.value)}
                    fullWidth={true}
                  />
                  <TextField
                    label="Years of Experience"
                    type="number"
                    value={experience + ''}
                    onChange={(event) =>
                      setExperience(parseInt(event.target.value))
                    }
                    fullWidth={true}
                  />
                  <TextField
                    label="License Number"
                    type="text"
                    value={lNumber}
                    onChange={(event) => setLNumber(event.target.value)}
                    fullWidth={true}
                  />
                  {/* dropdown */}
                  <CustomSelect
                    label="Qualification"
                    type="text"
                    value={qualifications}
                    onChange={(event) =>
                      setQualifications((event.target as any).value)
                    }
                    fullWidth={true}
                    options={QUALIFICATION_OPTIONS}
                    multiple={true}
                    className={classes.dropdown}
                  />
                  <MuiTextField
                    type="file"
                    label="Clinic Photo"
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                      if (event.target.files && event.target.files.length) {
                        setClinicPhoto(event.target.files[0]);
                      }
                    }}
                    inputProps={{
                      accept: 'image/*'
                    }}
                  />
                  {/* Vet availability container */}
                  <div className={classes.rootSlotsContainer}>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                      <Typography
                        className={classes.customLabel}
                        variant="h6"
                        align="left"
                        gutterBottom
                      >
                        Provide your weekly availability
                      </Typography>
                      <FormControlLabel
                        onChange={(event) => {
                          setDefaultAvl(event.target.checked);
                        }}
                        control={<Checkbox />}
                        label="check this to default for every input"
                      />
                      <Typography align="left" gutterBottom>
                        Monday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          value={availability['MONDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) => {
                            handleVetAvlChange(event, 'start');
                          }}
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          value={availability['MONDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) => {
                            handleVetAvlChange(event, 'end');
                          }}
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Tuesday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['TUESDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                TUESDAY: {
                                  ...availability['TUESDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['TUESDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                TUESDAY: {
                                  ...availability['TUESDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Wednesday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['WEDNESDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                WEDNESDAY: {
                                  ...availability['WEDNESDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['WEDNESDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                WEDNESDAY: {
                                  ...availability['WEDNESDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Thursday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['THURSDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                THURSDAY: {
                                  ...availability['THURSDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['THURSDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                THURSDAY: {
                                  ...availability['THURSDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Friday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['FRIDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                FRIDAY: {
                                  ...availability['FRIDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['FRIDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                FRIDAY: {
                                  ...availability['FRIDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Saturday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['SATURDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                SATURDAY: {
                                  ...availability['SATURDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['SATURDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                SATURDAY: {
                                  ...availability['SATURDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>

                      <Typography align="left" gutterBottom>
                        Sunday:
                      </Typography>
                      <div className={classes.slotContainer}>
                        <TimePicker
                          label="Start Time"
                          readOnly={defaultAvl}
                          value={availability['SUNDAY'].start}
                          className={classes.slotContainerStart}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                SUNDAY: {
                                  ...availability['SUNDAY'],
                                  start: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                        <TimePicker
                          label="End Time"
                          readOnly={defaultAvl}
                          value={availability['SUNDAY'].end}
                          className={classes.slotContainerEnd}
                          onChange={(event) =>
                            setAvailability((prev) => {
                              return {
                                ...prev,
                                SUNDAY: {
                                  ...availability['SUNDAY'],
                                  end: event!
                                }
                              };
                            })
                          }
                          format="HH:mm"
                        />
                      </div>
                    </LocalizationProvider>
                  </div>
                </div>
              </>
            ) : selectedOption === 'PET_OWNER' ? (
              <>
                <TextField
                  label="Address"
                  type="text"
                  value={clinicAddress}
                  onChange={(event) => setClinicAddress(event.target.value)}
                  fullWidth={true}
                />
                <MuiTextField
                  type="file"
                  label="Profile Photo"
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                    if (event.target.files && event.target.files.length) {
                      setProfilePhoto(event.target.files[0]);
                    }
                  }}
                  inputProps={{
                    accept: 'image/*'
                  }}
                />
              </>
            ) : (
              <></>
            )}
          </div>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={!email || !password}
            fullWidth={true}
            className={classes.submitButton}
          >
            Sign Up
          </Button>
        </form>
        <Typography variant="body2" align="center">
          Already have an account?{' '}
          <Link component={RouterLink} to="/signin">
            Sign In
          </Link>
        </Typography>
      </Container>
    </div>
  );
};

export default SignUp;

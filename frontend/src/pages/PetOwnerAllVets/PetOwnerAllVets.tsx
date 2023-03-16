import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext } from '@src/context';
import VetCardList from './VetCardList';
import { VetsState } from '@src/types';
import { vetsData } from '@src/data';
import useStyles from './PetOwnerAllVets.styles';

const PetOwnerAllVets: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [vetsState, setVetsState] = useState<VetsState>(vetsData);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Veterinarian',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <VetCardList vets={vetsState.vets} />
    </div>
  );
};

export default PetOwnerAllVets;

/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext } from '@src/context';
import VetCardList from './VetCardList';
import { vetsData } from '@src/data';
import { Loader, EmptyState } from '@src/components';
import useStyles from './PetOwnerAllVets.styles';

const PetOwnerAllVets = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [vetsState, setVetsState] = useState({
    vets: []
  });
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Veterinarian',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });

    setIsLoading(true);
    setTimeout(() => {
      setVetsState(vetsData);
      setIsLoading(false);
    }, 3000);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (vetsState?.vets?.length === 0) {
      return <EmptyState />;
    }

    return <VetCardList vets={vetsState.vets} />;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default PetOwnerAllVets;

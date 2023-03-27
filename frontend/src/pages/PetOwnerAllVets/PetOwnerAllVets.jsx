/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext } from '@src/context';
import VetCardList from './VetCardList';
import { Loader, EmptyState } from '@src/components';
import useStyles from './PetOwnerAllVets.styles';
import { getAllVets } from '@src/api';

const PetOwnerAllVets = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();

  const [vetsState, setVetsState] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Veterinarian',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });

    async function fetchData() {
      try {
        setIsLoading(true);
        const result = await getAllVets();
        setIsLoading(false);

        setVetsState(result);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (vetsState?.length === 0) {
      return <EmptyState text={'No Vets Available!'} />;
    }

    return <VetCardList vets={vetsState} />;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default PetOwnerAllVets;

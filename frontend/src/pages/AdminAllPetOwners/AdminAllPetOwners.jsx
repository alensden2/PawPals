// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import useStyles from './AdminAllPetOwners.styles';
import { HeaderContext } from '@src/context';
import { getAllPetOwners } from '@src/api';
import { Loader, EmptyState } from '@src/components';
import AllPetOwnersTable from './AllPetOwnersTable';

const AdminAllPetOwners = () => {
  const classes = useStyles();
  const [petOwners, setPetOwners] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Pet Owners',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/admin/home'
    });

    async function fetchData() {
      try {
        setIsLoading(true);
        const response = await getAllPetOwners();
        setPetOwners(response);
        setIsLoading(false);
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

    if (petOwners?.length === 0) {
      return <EmptyState text={'No Pet Owners in the system yet!'} />;
    }

    return <div>{<AllPetOwnersTable petOwners={petOwners} />}</div>;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default AdminAllPetOwners;

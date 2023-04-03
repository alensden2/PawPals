/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import useStyles from './AdminAllVets.styles';
import { HeaderContext } from '@src/context';
import { getAllVets } from '@src/api';
import AllVetsTable from './AllVetsTable';
import { Loader, EmptyState } from '@src/components';

const AdminAllVets = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);
  const [vets, setVets] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Vets',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/admin/home'
    });

    async function fetchData() {
      try {
        setIsLoading(true);
        const response = await getAllVets();
        setVets(response);
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

    if (vets?.length === 0) {
      return <EmptyState text={'No Veterinarians in the system yet!'} />;
    }

    return <AllVetsTable vets={vets} />;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default AdminAllVets;

/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useState } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import useStyles from './AllPetOwnersTable.styles';
import TextField from '@material-ui/core/TextField';

const AllPetOwnersTable = ({ petOwners }) => {
  const classes = useStyles();
  const [searchQuery, setSearchQuery] = useState('');
  const filteredData = petOwners.filter(
    (vet) =>
      `${vet.userName} ${vet.firstName} ${vet.lastName} ${vet.phoneNo}`
        .toLowerCase()
        .includes(searchQuery.toLowerCase()) && vet.profileStatus !== 'PENDING'
  );

  return (
    <div>
      <TextField
        className={classes.searchBar}
        id="search"
        label="Search"
        variant="outlined"
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
      />
      <TableContainer component={Paper} className={classes.container}>
        <Table className={classes.table} aria-label="vet table">
          <TableHead>
            <TableRow className={classes.headerRow}>
              <TableCell className={classes.headerCell}>User Name</TableCell>
              <TableCell className={classes.headerCell}>First Name</TableCell>
              <TableCell className={classes.headerCell}>Last Name</TableCell>
              <TableCell className={classes.headerCell}>Phone Number</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredData.map((vet) => (
              <TableRow key={vet.firstName}>
                <TableCell component="th" scope="row">
                  {vet.userName}
                </TableCell>
                <TableCell component="th" scope="row">
                  {vet.firstName}
                </TableCell>
                <TableCell>{vet.lastName}</TableCell>
                <TableCell>{vet.phoneNo}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default AllPetOwnersTable;

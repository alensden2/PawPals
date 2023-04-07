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
import useStyles from './AllVetsTable.styles';
import TextField from '@material-ui/core/TextField';

const AllVetsTable = ({ vets }) => {
  const classes = useStyles();
  const [searchQuery, setSearchQuery] = useState('');
  const filteredData = vets.filter(
    (vet) =>
      `${vet.userName} ${vet.firstName} ${vet.lastName} ${vet.phoneNo} ${vet.qualification} ${vet.email} ${vet.licenseNumber} ${vet.experience}`
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
              <TableCell className={classes.headerCell}>
                Qualification
              </TableCell>
              <TableCell className={classes.headerCell}>Email</TableCell>
              <TableCell className={classes.headerCell}>
                License Number
              </TableCell>
              <TableCell className={classes.headerCell}>Experience</TableCell>
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
                <TableCell>{vet.qualification}</TableCell>
                <TableCell>{vet.email}</TableCell>
                <TableCell>{vet.licenseNumber}</TableCell>
                <TableCell>{`${vet.experience} year(s)`}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default AllVetsTable;

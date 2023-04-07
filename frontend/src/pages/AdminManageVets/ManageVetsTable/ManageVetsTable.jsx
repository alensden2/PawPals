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
import Button from '@material-ui/core/Button';
import useStyles from './ManageVetsTable.styles';
import TextField from '@material-ui/core/TextField';

const ManageVetsTable = ({
  pendingVets,
  handleApproveVet,
  handleRejectVet
}) => {
  const classes = useStyles();
  const [searchQuery, setSearchQuery] = useState('');
  const filteredData = pendingVets.filter(
    (vet) =>
      `${vet.firstName} ${vet.lastName} ${vet.phoneNo} ${vet.qualification} ${vet.email}`
        .toLowerCase()
        .includes(searchQuery.toLowerCase()) && vet.profileStatus === 'PENDING'
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
              <TableCell className={classes.headerCell}>Approve Vet</TableCell>
              <TableCell className={classes.headerCell}>Reject Vet</TableCell>
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
                <TableCell>
                  <Button
                    size="small"
                    variant="outlined"
                    color="primary"
                    onClick={() => handleApproveVet(vet)}
                    className={classes.button}
                  >
                    Approve
                  </Button>
                </TableCell>
                <TableCell>
                  <Button
                    size="small"
                    variant="outlined"
                    color="secondary"
                    onClick={() => handleRejectVet(vet)}
                    className={classes.button}
                  >
                    Reject
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default ManageVetsTable;

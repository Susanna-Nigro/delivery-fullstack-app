import React, { useState } from "react"

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from 'axios';


export default function BasicTable() {    
    
  const[orders, setOrders] = useState([])

  const retrieveOrders = async () => {
    const response = await axios('http://localhost:8080/api/order');
    setOrders(response.data);
  }

  return (
    <div>
        <button onClick={() => retrieveOrders()}>Mostra ordini</button>
        <hr/>
        
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
            <TableRow>
                <TableCell>Order&nbsp;id</TableCell>
                <TableCell align="right">Created&nbsp;at</TableCell>
                <TableCell align="right">Shipping&nbsp;address</TableCell>
                <TableCell align="right">Status</TableCell>
                <TableCell align="right">Depot&nbsp;id</TableCell>
                <TableCell align="right">Supplier&nbsp;id</TableCell>
            </TableRow>
            </TableHead>

            <TableBody>
            {orders.map((order) => (
                <TableRow
                key={order.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell component="th" scope="row">{order.id}</TableCell>
                  <TableCell align="right">{order.createdAt}</TableCell>
                  <TableCell component="th" scope="row">{order.shippingAddress}</TableCell>
                  <TableCell align="right">{order.status}</TableCell>
                  <TableCell component="th" scope="row">{order.depot.address}</TableCell>
                  <TableCell align="right">{order.supplier.name}</TableCell>                  
                </TableRow>
            ))}
            </TableBody>

        </Table>
        </TableContainer>
    </div>
  );
}
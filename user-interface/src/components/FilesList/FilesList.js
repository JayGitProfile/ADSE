import React from 'react';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import './FilesList.css'
import axios from 'axios';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Tooltip } from '@mui/material';
import Modal from '@mui/material/Modal';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { useEffect } from 'react';
import { Editor } from 'react-draft-wysiwyg';
import '../../../node_modules/react-draft-wysiwyg/dist/react-draft-wysiwyg.css';

function FilesList() {

  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '60%',
    height: '68%',
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };

  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });
  
  const [Deleteopen, setDeleteOpen] = React.useState(false);
  const [EditOpen, setEditOpen] = React.useState(false);

  const handleDeleteOpen = () => {
    setDeleteOpen(true);
  };

  const handleDeleteClose = () => {
    setDeleteOpen(false);
  };

  const handleEditOpen = () => {
    setEditOpen(true);
  };

  const handleEditClose = () => {
    setEditOpen(false);
  };

  let files = [{name:'largeFile', type:'mkv',size:12},{name:'small file', type:'txt',size:0.2}]
  useEffect(() => {
    axios.get().then(data => {
      files = JSON.parse(JSON.stringify(data));
    })
  });


  return (
    <div className="FilesList">
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="right">File Name</TableCell>
            <TableCell align="right">File Size&nbsp;(mb)</TableCell>
            <TableCell align="right">Created Date</TableCell>
            <TableCell align="right">Created By</TableCell>
            <TableCell align="right">Last Modified Date</TableCell>
            <TableCell align="right">Last Modified By</TableCell>
            <TableCell align="right">Sync Status</TableCell>
            <TableCell align="right"> &nbsp; &nbsp; &nbsp;</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {files.map((row) => (
            <TableRow
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row" align="right">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.size}</TableCell>
              <TableCell align="right">{row.createddate}</TableCell>
              <TableCell align="right">{row.createdby}</TableCell>
              <TableCell align="right">{row.lastmodified}</TableCell>
              <TableCell align="right">{row.modifiedby}</TableCell>
              <TableCell align="right">{row.syncstatus}</TableCell>
              <TableCell align="right">{row.type == 'txt' ?  <span className='EditIcon'><Tooltip title="Edit"
              placement="top"><EditIcon onClick={handleEditOpen}></EditIcon></Tooltip></span> : ''} 
              <span className='DeleteIcon'><Tooltip title="Delete"
              placement="top"><DeleteIcon onClick={handleDeleteOpen}></DeleteIcon></Tooltip></span></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>

    <Dialog
        open={Deleteopen}
        TransitionComponent={Transition}
        keepMounted
        onClose={handleDeleteClose}
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle>{"Do you want to delete this file?"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-slide-description">
            This action will delete the file for all clients and is <b>irreversible</b>.
            do you want to delete it anyway?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteClose}>Cancel</Button>
          <Button onClick={handleDeleteClose}><span style={{color:'red'}}>Delete</span></Button>
        </DialogActions>
      </Dialog>

    <Modal
      open={EditOpen}
      onClose={handleEditClose}
    >
      <Box sx={style}>
      <Editor /></Box>
    </Modal>
    </div>
  );
 }

FilesList.propTypes = {};

FilesList.defaultProps = {};

export default FilesList;

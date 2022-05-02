import './App.css';
import Gradient from 'rgt'
import FilesList from './components/FilesList/FilesList';
import PauseCircleOutlineOutlinedIcon from '@mui/icons-material/PauseCircleOutlineOutlined';
import CachedOutlinedIcon from '@mui/icons-material/CachedOutlined';
import { Tooltip } from '@mui/material';
import { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  const [isSuspended,setisSuspended] = useState(false);
  function suspendorresume() {
    setisSuspended(!isSuspended)
    toast.dismiss()
    if(!isSuspended) {
      toast.error("All tasks and transfers are now suspended!");
    } else {
      toast.success("All tasks and transfers are now resumed");
    }
  }
  return (
    <div className="App">
     <p className='gradient-text'>
      <span>
        <Gradient dir="left-to-right" from="#00DFD8" to="#007CF0"> WELCOME TO</Gradient>
        <Gradient dir="left-to-right" from="#ffeb3b" to="#e91e63"> SYNC-FE</Gradient>
      </span>

      <span onClick={suspendorresume}>
        { !isSuspended ? 
       <Tooltip
       title="suspend"
       placement="top">
     <PauseCircleOutlineOutlinedIcon fontSize='large'/></Tooltip> : 
     <Tooltip
     title="resume"
     placement="top"><CachedOutlinedIcon fontSize='large'/>
       </Tooltip> }
      </span>
     </p>

     <FilesList/>

     <ToastContainer
        position="bottom-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />

    </div>
  );
}

export default App;

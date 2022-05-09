import { useEffect, useState } from "react";
import React from 'react';
import axios from "axios";
import { Button } from "@mui/material";
import Pagination from '@mui/material/Pagination';
import './TextEditor.css'
function TextEditor({fileName}) {
  const [page,setpage]=useState(1)
  const [totalpages,setTotalPages]=useState(0);
  const [data,setData] = useState('');
   useEffect(()=> {
  do{
    axios.get(`http://localhost:8080/file/read/${fileName}`).then((data)=>{
      debugger
      setpage(data.page);
      setData(data[fileName]);
      setTotalPages(data.totalPages);
   })}while(false)
  })
  const [changed, setchanged] = useState(false);
  function changeContent(event) {
    setData(event.target.value);
    setchanged(true);
  }

  function updateContent() {
    axios.post('http://localhost:8080/file/update', {
      fileName: fileName,
      newContent:data,
      original: '',
      pageIndex: page
    })
  }

  function updatePage(event) {
    axios.get(`http://localhost:8080/file/read/${fileName}/${event.target.value}/${totalpages}`).then((data) => {
      setpage(data.page);
      setData(data[fileName]);
      setTotalPages(data.totalPages);
    })
  }

  return (<div className="editor-field"><textarea value={data} onChange={changeContent}></textarea>
    <div className="footer">
      <span></span>
      <Pagination count={totalpages} page={page} onClick={updatePage} variant="outlined" color="secondary" />
      <Button variant="outlined" color="success" onClick={updateContent} disabled={!changed}>
        Update
      </Button></div></div>)
}



export default TextEditor;

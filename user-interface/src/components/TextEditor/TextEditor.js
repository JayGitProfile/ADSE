import { useState } from "react";
import React from 'react';
import axios from "axios";
import { Button } from "@mui/material";
import Pagination from '@mui/material/Pagination';
import './TextEditor.css'
function TextEditor({originalFile}) {
  debugger
  let selectedFile = JSON.parse(JSON.stringify(originalFile));
  let currentPage = 1;
  const [changed, setchanged] = useState(false);
  function changeContent(event) {
    selectedFile[selectedFile.fileName] = event.target.value;
    if(selectedFile[selectedFile.fileName] != originalFile[originalFile.fileName]) {
      setchanged(true);
    }
  }

  function updateContent() {
    axios.post('http://localhost:8080/file/update', {
      fileName: selectedFile.fileName,
      newContent: selectedFile[selectedFile.fileName],
      original: originalFile[selectedFile.fileName],
      pageIndex: currentPage
    })
  }

  function updatePage(event) {
    axios.get(`http://localhost:8080/file/read/${selectedFile.fileName}/${event.target.value}/${selectedFile.totalPages}`).then((data) => {
      selectedFile = data;
    })
  }

  return (<div className="editor-field"><textarea value={selectedFile[selectedFile.fileName]} onChange={changeContent}></textarea>
    <div className="footer">
      <span></span>
      <Pagination count={selectedFile.totalPages} page={selectedFile.page} onClick={updatePage} variant="outlined" color="secondary" />
      <Button variant="outlined" color="success" onClick={updateContent} disabled={!changed}>
        Update
      </Button></div></div>)
}



export default TextEditor;

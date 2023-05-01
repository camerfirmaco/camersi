import React, { useEffect, useState } from 'react';
import './index.css';
import * as XLSX from 'xlsx';
const App: React.FC = () => {

  const readUploadFile = (e: any) => {
    e.preventDefault();
    if (e.target.files) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const data = e.target?.result;
        const workbook = XLSX.read(data);
        const array: Object[] = [];
        for (let index = 0; index <= 2; index++) {
          const worksheet = workbook.Sheets[workbook.SheetNames[index]];
          const json = XLSX.utils.sheet_to_json(worksheet);
          if (index === 0)
            array.push({ TOKEN: json });
          if (index === 1)
            array.push({ CKC: json });
          if (index === 2)
            array.push({ TOP: json });
        }
        console.log(array);
      };
      reader.readAsArrayBuffer(e.target.files[0]);
    }
  }

  return (
    <form>
      <label htmlFor="upload">Upload File</label>
      <input
        type="file"
        name="upload"
        id="upload"
        onChange={readUploadFile}
      />
    </form>
  );
};

export default App;
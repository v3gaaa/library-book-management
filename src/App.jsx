import React, { useState } from "react";
import "./App.css";
import SearchComponent from "./components/SearchComponent";
import DataGrid from "./components/DataGrid";

function App() {
  const [books, setBooks] = useState([]);

  return (
    <>
      <h1>Library Book Management</h1>
      <SearchComponent setBooks={setBooks} />
      <DataGrid books={books} />
    </>
  );
}

export default App;

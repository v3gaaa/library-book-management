import React, { useState } from "react";
import "./styles/DataGrid.css";

const DataGrid = ({ books }) => {
  const maxlibros = 5;
  const [currentPage, setCurrentPage] = useState(1);



  //PAGINACION
  const indexOfLastBook = currentPage * maxlibros;
  const indexOfFirstBook = indexOfLastBook - maxlibros;
  const currentBooks = books.slice(indexOfFirstBook, indexOfLastBook);


  const totalPages = Math.ceil(books.length / maxlibros);

  const nextPage = () => {
    if (currentPage < totalPages) setCurrentPage(currentPage + 1);
  };

  const prevPage = () => {
    if (currentPage > 1) setCurrentPage(currentPage - 1);
  };

  return (
    <div className="grid-container">
      <table className="book-table">


        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Published Date</th>
            <th>ISBN</th>
            <th>Availability</th>
          </tr>
        </thead>

        
        <tbody>
          {currentBooks.length > 0 ? (
            currentBooks.map((book) => (
              <tr key={book.id}>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.genre}</td>
                <td>{book.publishedDate}</td>
                <td>{book.isbn}</td>
                <td className={book.availability ? "available" : "unavailable"}>
                  {book.availability ? "Available" : "Unavailable"}
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6">No books found</td>
            </tr>
          )}
        </tbody>
      </table>

      {/*PAGINACION */}
      {books.length > maxlibros && (
        <div className="pagination">
          <button onClick={prevPage} disabled={currentPage === 1}>
            Previous
          </button>
          <span>
            Page {currentPage} of {totalPages}
          </span>
          <button onClick={nextPage} disabled={currentPage === totalPages}>
            Next
          </button>
        </div>
      )}
    </div>
  );
};

export default DataGrid;

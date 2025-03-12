import React, { useState, useEffect } from "react";
import axios from "axios";
import "./styles/SearchComponent.css";

const SearchComponent = ({ setBooks }) => {
  const [query, setQuery] = useState("");
  const [error, setError] = useState(null); // Estado para errores

  const handleInputChange = (event) => {
    setQuery(event.target.value);
  };

  useEffect(() => {
    if (query.trim() === "") {
      setBooks([]);
      setError(null);
      return;
    }

    const fetchBooks = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/books`, {
          params: { search: query },
        });
        setBooks(response.data);
        setError(null);
      } catch (err) {
        setError("Failed to fetch books. Please try again."); //ERROR HANDLING
      }
    };

    const timeoutId = setTimeout(fetchBooks, 500); //DEBOUNCE
    return () => clearTimeout(timeoutId);
  }, [query, setBooks]);

  return (
    <div className="container">
      <input
        type="text"
        placeholder="Search books..."
        className="search-box"
        value={query}
        onChange={handleInputChange}
      />
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default SearchComponent;

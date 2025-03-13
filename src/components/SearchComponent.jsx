import React, { useState, useEffect } from "react";
import axios from "axios";
import "./styles/SearchComponent.css";

const SearchComponent = ({ setBooks }) => {
  const [query, setQuery] = useState("");
  const [error, setError] = useState(null);
  const [useMockData, setUseMockData] = useState(false);

  const handleInputChange = (event) => {
    setQuery(event.target.value);
  };

  const toggleDataSource = () => {
    setUseMockData((prev) => !prev);
  };

  useEffect(() => {
    if (query.trim() === "") {
      setBooks([]);
      setError(null);
      return;
    }

    const fetchBooks = async () => {
      try {
        const endpoint = useMockData
          ? "http://localhost:8080/api/books/mock-books"
          : "http://localhost:8080/api/books";

        const response = await axios.get(endpoint, { params: { search: query } });
        setBooks(response.data);
        setError(null);
      } catch (err) {
        setError("Failed to fetch books. Please try again.");
      }
    };

    const timeoutId = setTimeout(fetchBooks, 500); // DEBOUNCE
    return () => clearTimeout(timeoutId);
  }, [query, setBooks, useMockData]);

  return (
    <div className="container">
      <button className="toggle-button" onClick={toggleDataSource}>
        {useMockData ? "Use Database Data" : "Use Mock Data"}
      </button>

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

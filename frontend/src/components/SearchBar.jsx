import { useState } from 'react';
import axios from 'axios';

export default function SearchBar({ onSearch }) {
  const [searchParams, setSearchParams] = useState({
    title: '',
    author: '',
    content: '',
    tags: '',
    category: ''  // Added category field
  });
  const [searchType, setSearchType] = useState('all');

  const handleSearch = async () => {
    try {
      let response;
      const baseUrl = 'http://localhost:8080/api/notes';
      
      if (searchType === 'all') {
        // Advanced search with multiple parameters including category
        response = await axios.get(`${baseUrl}/search/advanced`, {
          params: {
            title: searchParams.title,
            author: searchParams.author,
            content: searchParams.content,
            tag: searchParams.tags,
            category: searchParams.category  // Added category
          }
        });
      } else {
        // Specific field search
        const endpointMap = {
          title: '/search/title',
          author: '/search/author',
          content: '/search/content',
          tags: '/search/tags',
          category: '/search/category'  
        };
        response = await axios.get(`${baseUrl}${endpointMap[searchType]}`, {
          params: { [searchType]: searchParams[searchType] }
        });
      }
      
      onSearch(response.data);
    } catch (error) {
      console.error('Search error:', error);
      onSearch({"error": error.message});
    }
  };

  const handleInputChange = (field, value) => {
    setSearchParams(prev => ({ ...prev, [field]: value }));
  };

  return (
    <div className="search-container">
      <div className="search-controls">
        <select 
          value={searchType}
          onChange={(e) => setSearchType(e.target.value)}
          className="search-type"
        >
          <option value="all">All Fields</option>
          <option value="title">Title</option>
          <option value="author">Author</option>
          <option value="content">Content</option>
          <option value="tags">Tags</option>
          <option value="category">Category</option> 
        </select>

        {searchType === 'all' ? (
          <div className="multi-search-fields">
            <input
              type="text"
              value={searchParams.title}
              onChange={(e) => handleInputChange('title', e.target.value)}
              placeholder="Title"
            />
            <input
              type="text"
              value={searchParams.author}
              onChange={(e) => handleInputChange('author', e.target.value)}
              placeholder="Author"
            />
            <input
              type="text"
              value={searchParams.content}
              onChange={(e) => handleInputChange('content', e.target.value)}
              placeholder="Content"
            />
            <input
              type="text"
              value={searchParams.tags}
              onChange={(e) => handleInputChange('tags', e.target.value)}
              placeholder="Tags (comma separated)"
            />
            <input
              type="text"
              value={searchParams.category}
              onChange={(e) => handleInputChange('category', e.target.value)}
              placeholder="Category"
            />
          </div>
        ) : (
          <input
            type="text"
            value={searchParams[searchType]}
            onChange={(e) => handleInputChange(searchType, e.target.value)}
            placeholder={`Search by ${searchType}...`}
            className="search-input"
          />
        )}

        <button onClick={handleSearch} className="search-button">
          Search
        </button>
      </div>
    </div>
  );
}
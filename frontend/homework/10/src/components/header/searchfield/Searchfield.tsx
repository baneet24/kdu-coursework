// Searchfield.tsx
import React from 'react';
import './Searchfield.scss';

interface SearchfieldProps {
  searchItem: string,
  setSearchItem: React.Dispatch<React.SetStateAction<string>>;
}

export const Searchfield = ({ searchItem, setSearchItem}: SearchfieldProps) => {

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchVal = event.target.value;
    setSearchItem(searchVal);
  };



  return <input type="text" className="search-field" placeholder="Search Items.." name="search" value={searchItem} onChange={handleSearchChange} />;
};

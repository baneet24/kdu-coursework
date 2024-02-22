// Searchfield.tsx
import React, { useState } from 'react';
import './Searchfield.scss';

interface SearchfieldProps {
  searchItem: string,
  setSearchItem: React.Dispatch<React.SetStateAction<string>>;
}

export const Searchfield = ({ searchItem, setSearchItem }: SearchfieldProps) => {

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchVal = event.target.value;
    setSearchItem(searchVal);
  };

  return <input type="text" placeholder="Search Items.." name="search" value={searchItem} onChange={handleSearchChange} />;
};

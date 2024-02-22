import React from 'react';
import './Searchfield.scss';
import { useTodoContext } from '../../../Util/TodoContext';

export const Searchfield = () => {
  const { searchItem, setSearchItem } = useTodoContext();

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchItem(e.target.value);
  };

  return (
    <div>
      <input type="text" className="search-field" placeholder="Search Items.." value={searchItem} onChange={handleSearchChange} />
    </div>
  );
};

// Header.tsx
import React from 'react';
import { Searchfield } from './searchfield/Searchfield';
import { Headingtext } from './Headingtext.tsx/Headingtext';
import './Header.scss';

interface HeaderProps {
    searchItem: string,
    setsearchItem: React.Dispatch<React.SetStateAction<string>>
  }
  
  export const Header = ({ searchItem, setsearchItem}: HeaderProps) => {
    return (
      <div className="header-container">
        <Headingtext/>
        <Searchfield  searchItem={searchItem} setSearchItem={setsearchItem} />
      </div>
    );
  };

import React from 'react';
import { Searchfield } from './searchfield/Searchfield';
import { Headingtext } from './Headingtext.tsx/Headingtext';
import './Header.scss';

  
  export const Header = () => {
    return (
      <div className="header-container">
        <Headingtext />
        <Searchfield />
      </div>
    );
  };

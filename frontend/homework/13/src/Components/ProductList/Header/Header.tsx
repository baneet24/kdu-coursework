import React, { useState } from "react";

interface HeaderProps {
  // Add any additional props if needed
}

export function Header({}: HeaderProps) {
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [selectedOption1, setSelectedOption1] = useState<string>("");
  const [selectedOption2, setSelectedOption2] = useState<string>("");

  // Handle search query change
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value);
  };

  // Handle dropdown selection change
  const handleDropdownChange = (
    dropdownNumber: number,
    selectedValue: string
  ) => {
    if (dropdownNumber === 1) {
      setSelectedOption1(selectedValue);
    } else if (dropdownNumber === 2) {
      setSelectedOption2(selectedValue);
    }
  };

  const dropdownOptions = ["Option 1", "Option 2", "Option 3"];

  return (
    <div>
      <h1>Your App</h1>

      <input
        type="text"
        placeholder="Search..."
        value={searchQuery}
        onChange={handleSearchChange}
      />


      <select
        value={selectedOption1}
        onChange={(e) => handleDropdownChange(1, e.target.value)}
      >
        <option value="">Select Option 1</option>
        {dropdownOptions.map((option, index) => (
          <option key={index} value={option}>
            {option}
          </option>
        ))}
      </select>

      {/* Dropdown 2 */}
      <select
        value={selectedOption2}
        onChange={(e) => handleDropdownChange(2, e.target.value)}
      >
        <option value="">Select Option 2</option>
        {dropdownOptions.map((option, index) => (
          <option key={index} value={option}>
            {option}
          </option>
        ))}
      </select>
    </div>
  );
}

export default Header;

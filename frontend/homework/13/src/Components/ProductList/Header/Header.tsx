import React, { useState } from "react";

  return (
    <div>
      <h1>E-commerce</h1>

      <input
        type="text"
        placeholder="Search..."
        value={searchQuery}
        onChange={handleSearchChange}
      />
    </div>
  );
}

export default Header;

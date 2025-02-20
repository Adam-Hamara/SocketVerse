import React, { useState } from 'react';

function Home() {
  const [isVisible, setIsVisible] = useState(false);

  const toggleVisibility = () => {
    setIsVisible(!isVisible);
  };

  return (
    <div className="page">
      <h1>Home Page</h1>
      <button onClick={toggleVisibility}>
        {isVisible ? 'Hide Message' : 'Show Message'}
      </button>
      {isVisible && <p>Hello! This message is now visible.</p>}
    </div>
  );
}

export default Home;
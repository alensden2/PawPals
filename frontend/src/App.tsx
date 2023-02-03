import React from 'react';
import { Link, Outlet } from 'react-router-dom';

function App() {
  return (
    <div>
      <nav
        style={{
          borderBottom: 'solid 1px',
          paddingBottom: '1rem'
        }}
      >
        <Link to="/aboutme">{'aboutme '}</Link>
        <Link to="/landing">{'landing'}</Link>
      </nav>
      <Outlet />
    </div>
  );
}

export default App;

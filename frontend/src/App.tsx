import React, { useEffect } from 'react';
import { Link, Outlet } from 'react-router-dom';
import { axios } from './lib';

// TODO:CLEANUP REMOVE THIS TEST FUNCTION
const getData = async () => {
  let advice = '';

  axios
    .get('advice', {})
    .then((response) => {
      advice = response.data.slip.advice;
    })
    .catch((error) => {
      console.log(error);
    });

  console.log(advice, 'advice');
};

function App() {
  useEffect(() => {
    getData();
  });

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

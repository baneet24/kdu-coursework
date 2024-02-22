import React from 'react';
import { Header } from './components/header/Header';
import { TodoContainer } from './components/todoContiner/TodoContainer';
import { TodoProvider } from './Util/TodoContext';

const App = () => {
  return (
    <TodoProvider>
      <div>
        <Header />
        <TodoContainer />
      </div>
    </TodoProvider>
  );
};

export default App;

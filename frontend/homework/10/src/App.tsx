import React, { useState } from 'react';
import { Header } from './components/header/Header';
import { TodoContainer } from './components/todoContiner/TodoContainer';


const App: React.FC = () => {
  const [searchItem, setSearchItem] = useState<string>('');
  const [todoList, setTodoList] = useState<string[]>(['Item 1', 'Item 2', 'Item 3']);

  return (
    <div>
      <Header searchItem = {searchItem} setsearchItem={setSearchItem} />
      <TodoContainer initialList={todoList} setTodoList = {setTodoList} searchItem = {searchItem}/>
    </div>
  );
};

export default App;

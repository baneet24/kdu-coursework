import React, { useState } from 'react';
import { Header } from './components/header/Header';
import { TodoContainer } from './components/todoContiner/TodoContainer';


const App: React.FC = () => {
  const [searchItem, setSearchItem] = useState<string>('');
  const [todoList, setTodoList] = useState<string[]>([]);

  return (
    <div>
      <Header searchItem = {searchItem} setsearchItem={setSearchItem} />
      <TodoContainer initialList={todoList} setTodoList = {setTodoList} searchItem = {searchItem}/>
    </div>
  );
};

export default App;

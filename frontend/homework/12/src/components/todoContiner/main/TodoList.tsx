import React from 'react';
import { useTodoContext } from '../../../Util/TodoContext';
import './TodoList.scss';

export const TodoList: React.FC = () => {
  const { todoList, setTodoList, searchItem } = useTodoContext();

  const newList = todoList.filter((item) =>
    item.toLowerCase().includes(searchItem.toLowerCase())
  );

  const handleDelete = (index: number) => {
    const updatedList = [...todoList];
    updatedList.splice(index, 1);
    setTodoList(updatedList);
  };

  return (
    <>
      <h2>Items</h2>
      {newList.length === 0 ? (
        <p>No matches found</p>
      ) : (
        <ul>
          {newList.map((item, index) => (
            <li key={index}>
              {item}
              <button onClick={() => handleDelete(index)}>&#x2715;</button>
            </li>
          ))}
        </ul>
      )}
    </>
  );
};

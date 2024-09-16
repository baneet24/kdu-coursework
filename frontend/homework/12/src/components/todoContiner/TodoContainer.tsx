import React from 'react';
import { InputField } from './InputItems/InputField';
import { TodoList } from './main/TodoList';
import './TodoContainer.scss';

export const TodoContainer = () => {
  return (
    <div className="main-container">
      <InputField />
      <TodoList/>
    </div>
  );
};

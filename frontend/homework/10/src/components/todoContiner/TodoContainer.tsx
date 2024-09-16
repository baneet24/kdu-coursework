import React, { useState } from "react";
import { InputField } from "./InputItems/InputField";
import { TodoList } from "./main/TodoList";
import './TodoContainer.scss'

interface TodoContainerProps {
  initialList: string[];
  setTodoList: React.Dispatch<React.SetStateAction<string[]>>;
  searchItem: string;
}

export const TodoContainer = ({
  initialList,
  setTodoList,
  searchItem,
}: TodoContainerProps) => {
  const [task, setTask] = useState<string>("");

  const handleDelete = (index: number) => {
    const updatedList = [...initialList];
    updatedList.splice(index, 1);
    setTodoList(updatedList);
  };

  return (
    <div className="main-container">
      <InputField
        task={task}
        setTask={setTask}
        todoList={initialList}
        setList={setTodoList}
      />

<TodoList todoList={initialList} onDelete={handleDelete} searchItem={searchItem} />
    </div>
  );
};

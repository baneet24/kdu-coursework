// TodoContainer.tsx
import React, { useState } from "react";
import { InputField } from "./InputItems/InputField";
import { TodoList } from "./main/TodoList";

interface ITodoItem {
  text: string;
}

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
  const [list, setList] = useState<string[]>(initialList);
  const [task, setTask] = useState<string>("");

  const handleDelete = (index: number) => {
    const updatedList = [...list];
    updatedList.splice(index, 1);
    setList(updatedList);
  };

  return (
    <div>
      <TodoList todoList={list} onDelete={handleDelete} />

      <InputField
        task={task}
        setTask={setTask}
        todoList={initialList}
        setList={setTodoList}
      />
    </div>
  );
};

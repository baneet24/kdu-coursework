import React, { createContext, useContext, useState } from "react";

interface TodoContextProps {
  children: React.ReactNode;
}

interface ITodoContext {
  searchItem: string;
  setSearchItem: React.Dispatch<React.SetStateAction<string>>;
  todoList: string[];
  setTodoList: React.Dispatch<React.SetStateAction<string[]>>;
}

const ToDoContextVal: ITodoContext = {
  searchItem: "",
  setSearchItem: () => {},
  todoList: [],
  setTodoList: () => {},
};

const TodoContext = createContext<ITodoContext>(ToDoContextVal);

export const TodoProvider: React.FC<TodoContextProps> = ({
  children,
}: TodoContextProps) => {
  const [searchItem, setSearchItem] = useState<string>("");
  const [todoList, setTodoList] = useState<string[]>([]);

  const contextValue: ITodoContext = {
    searchItem,
    setSearchItem,
    todoList,
    setTodoList,
  };

  return (
    <TodoContext.Provider value={contextValue}>{children}</TodoContext.Provider>
  );
};

export const useTodoContext = (): ITodoContext => {
  const context = useContext(TodoContext);
  return context;
};

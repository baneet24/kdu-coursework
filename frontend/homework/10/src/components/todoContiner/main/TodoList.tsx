interface TodoListProps {
  todoList: string[];
  onDelete: (index: number) => void;
}

export const TodoList = ({ todoList, onDelete }: TodoListProps) => {
  return (
    <ul>
      {todoList.map((item, index) => (
        <li key={index}>
          {item} 
          <button onClick={() => onDelete(index)}>Delete</button>
        </li>
      ))}
    </ul>
  );
};


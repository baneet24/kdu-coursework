import './TodoList.scss';

interface TodoListProps {
  todoList: string[];
  onDelete: (index: number) => void;
  searchItem: string;
}

export const TodoList = ({ todoList, onDelete, searchItem }: TodoListProps) => {

  const newList = todoList.filter((item)=>item.toLowerCase().includes(searchItem.toLowerCase()));
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
              <button onClick={() => onDelete(index)}>&#x2715;</button>
            </li>
          ))}
        </ul>
      )}
    </>
  );
};


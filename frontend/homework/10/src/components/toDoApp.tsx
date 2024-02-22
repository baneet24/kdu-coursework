import { useState } from "react"

interface Todo {
  id: number;
  name: string;
}

const TodoApp = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [newTodo, setNewTodo] = useState<string>('');
  const [searchTerm, setSearchTerm] = useState<string>('');

  const handleAddTodo = () => {
    if (newTodo.trim() !== '') {
      setTodos([...todos, { id: Date.now(), name: newTodo }]);
      setNewTodo('');
    }
  };

  const handleDeleteTodo = (id: number) => {
    const updatedTodos = todos.filter((todo) => todo.id !== id);
    setTodos(updatedTodos);
  };

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const filteredTodos = todos.filter((todo) =>
    todo.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <h1>TODO App</h1>
      <input
        type="text"
        placeholder="Add new todo"
        value={newTodo}
        onChange={(e) => setNewTodo(e.target.value)}
      />
      <button onClick={handleAddTodo}>Add</button>

      <input
        type="text"
        placeholder="Search todo"
        value={searchTerm}
        onChange={handleSearch}
      />

      {searchTerm === '' ? (
        <ul>
          {todos.map((todo) => (
            <li key={todo.id}>
              {todo.name}
              <button onClick={() => handleDeleteTodo(todo.id)}>Delete</button>
            </li>
          ))}
        </ul>
      ) : filteredTodos.length > 0 ? (
        <ul>
          {filteredTodos.map((todo) => (
            <li key={todo.id}>
              {todo.name}
              <button onClick={() => handleDeleteTodo(todo.id)}>Delete</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>No Match found</p>
      )}
    </div>
  );
};

export default TodoApp;

// const app = () => {
//     const [task, setTask] = useState('');
//     const [todoList, setTodoList] = useState([]);


    
// }

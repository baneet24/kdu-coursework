import React, {useState} from 'react';
import { useTodoContext } from '../../../Util/TodoContext';
import './InputField.scss';


export const InputField: React.FC = () => {
  const { todoList, setTodoList } = useTodoContext();
  const [task, setTask] = useState<string>('');

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTask(event.target.value);
    console.log(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (task.trim() !== '') {
      const updatedList = [...todoList, task];
      setTodoList(updatedList);
      setTask('');
      console.log('Updated List:', updatedList);
    }
  };

  return (
    <div className="Input-area">
      <p>Add Items</p>
      <form onSubmit={handleSubmit}>
        <input type="text" value={task} onChange={handleInputChange} />
        <button type="submit" className="submit">
          Submit
        </button>
      </form>
    </div>
  );
};

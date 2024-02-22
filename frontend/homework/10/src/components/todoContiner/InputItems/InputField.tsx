import React from 'react';
import './InputField.scss'

interface InputFieldProps {
  task: string;
  setTask: React.Dispatch<React.SetStateAction<string>>;
  todoList: string[];
  setList: React.Dispatch<React.SetStateAction<string[]>>;
}

export const InputField = ({ task, setTask, todoList, setList }: InputFieldProps) => {
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTask(event.target.value);
    console.log(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (task.trim() !== '') {
      const updatedList = [...todoList, task];
      setList(updatedList);
      setTask('');
      console.log('Updated List:', todoList); 
    }
  };

  return (
    <div className="Input-area">
    <p>Add Items</p>
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={task}
        onChange={handleInputChange}
      />
      <button type="submit" className="submit">Submit</button>
    </form>
    </div>
  );
};

import React from 'react';

interface InputFieldProps {
  task: string;
  setTask: React.Dispatch<React.SetStateAction<string>>,
  todoList: string[],
  setList: React.Dispatch<React.SetStateAction<string[]>>
}




export const InputField = ({task, setTask, todoList, setList}: InputFieldProps) => {

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTask(event.target.value);
    console.log(event.target.value);
    };

  const handleSubmit = (event: React.FormEvent) => {

    if (task.trim() !== '') {
      setList([...todoList, task]);
      setTask('');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Add a new task"
        value={task}
        onChange={handleInputChange}
      />
     <button onClick={handleSubmit} className="submit">Add item</button>
    </form>
  );
};


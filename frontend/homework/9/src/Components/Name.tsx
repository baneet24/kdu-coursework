import './CSS/Name.css'

interface NameProps {
  name: string;
  fullName: string;
}

export const Name = ({ name, fullName }: NameProps) => {
  return (
    <div className="name-container">
      <h1>{name}</h1>
      <h2>{fullName}</h2>
    </div>
  );
};

import './CSS/Hobbies.css'
import { HobbyItem } from './HobbiesItem';

interface Hobby {
  id: number;
  hobby: string;
}

interface HobbiesProps {
  hobbies: Hobby[];
}

export const Hobbies= ({ hobbies }: HobbiesProps) => {
  return (
    <div className="hobby-container">
      <h2>Hobbies</h2>
      <ul>
      {hobbies.map(Hobby => (
          <HobbyItem key={Hobby.id} hobby={Hobby.hobby} />
        ))}
      </ul>
    </div>
  );
};


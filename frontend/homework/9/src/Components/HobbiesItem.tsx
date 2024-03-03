import './CSS/HobbiesItem.css'

interface HobbyItemProps {
  hobby: string;
}

export const HobbyItem = ({ hobby }: HobbyItemProps) => {
  return <li className="hobby-item">{hobby}</li>;
};

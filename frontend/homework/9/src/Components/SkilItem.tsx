import './CSS/SkillItem.css'

interface SkillItemProps {
  skill: string;
}

export const SkillItem = ({ skill }: SkillItemProps) => {
  return <li className="skill-item">{skill}</li>;
};

import { SkillItem } from './SkilItem';
import './CSS/Skills.css'

interface Skill {
  id: number;
  skill: string;
}


interface SkillsProps {
  skills: Skill[];
}

export const Skills = ({ skills }: SkillsProps) => {
  return (
    <div className="skill-container">
      <h2>Skills</h2>
      <ul>
        {skills.map(Skill => (
          <SkillItem key={Skill.id} skill={Skill.skill} />
        ))}
      </ul>
    </div>
  );
};

export default Skills;

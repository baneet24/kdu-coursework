import './CSS/Qualification.css'

interface QualificationProps {
  qualification: string;
}

export const Qualification= ({ qualification }: QualificationProps) => {
  return (
    <div className="qualification-container">
      <h1>{qualification}</h1>
    </div>
  );
};


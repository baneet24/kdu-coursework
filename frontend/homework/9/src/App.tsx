import './App.css';
import {Hobbies} from './Components/Hobbies';
import {Name} from './Components/Name';
import {Qualification} from './Components/Qualification';
import {Skills} from './Components/Skills';
import { getRecordData } from './data/Data';

function App() {
  const data = getRecordData();
  return (
    <div className='main'>
      <Name name={data.name} fullName={data.fullName} />

      <Qualification qualification={data.qualification} />

      <div className="lists">
      <Skills skills={data.skills} />
      <Hobbies hobbies={data.hobbies} />
      </div>
    </div>
  );
}

export default App;

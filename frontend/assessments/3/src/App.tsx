import { HotelPage } from "./components/HotelPage";
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
  AppClass: {
   padding: '0',
   margin: '0',
   fontFamily: 'Inter, system-ui, Avenir, Helvetica, Arial, sans-serif'
  },
});



function App() {
const styles = useStyles();
  return (
    <div className={`app ${styles.AppClass}`}>
      <HotelPage/>
    </div>
  )
}

export default App

import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import { MutableValues } from "./pages/MutableElementsPage/Mutable";
import { ScrollToTopButton } from "./pages/ScrollPage/scroll";
import { Timer } from "./pages/TimerPage/Timer";
import { FormPage } from "./pages/FormPage/FormPage";

const Home = () => <div>Home Page</div>;

const styles = {
  app:{
  
  },
  navbar: {
    backgroundColor: "black",
    position: "absolute",
    top: "0",
    display: "flex",
    paddingLeft: "9rem",
    poaddingBottom:"6rem",
    listStyle: "none"
  }, 

navbarItem:{
  color: "white",
  paddingLeft: "5rem"
}

};

const App = () => {
  return (
    <Router>
      <div>
        <nav>
          <ul style={styles.navbar}>
            <li style={styles.navbarItem}>
              <Link to="/">Home</Link>
            </li>
            <li style={styles.navbarItem}>
              <Link to="/mutable-values">Mutable Values</Link>
            </li>
            <li style={styles.navbarItem}>
              <Link to="/scroll-to-top">Scroll To Top</Link>
            </li>
            <li style={styles.navbarItem}>
              <Link to="/timer">Timer</Link>
            </li>
            <li style={styles.navbarItem}>
              <Link to="/form">Form Page</Link>
            </li>
          </ul>
        </nav>

        <hr />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/mutable-values" element={<MutableValues />} />
          <Route path="/scroll-to-top" element={<ScrollToTopButton />} />
          <Route path="/timer" element={<Timer />} />
          <Route path="/form" element={<FormPage />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;

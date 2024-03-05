import { createUseStyles } from "react-jss";
import { Link, useLocation } from "react-router-dom";

const useStyles = createUseStyles({
  exploreWatchlistButtons: {
    display: "flex",
    justifyContent: "left",
    padding: "1rem",
   
  },
  exploreButton: {
    cursor: "pointer",
    margin: "0.5rem 1rem",
    border: "none",
    backgroundColor: "transparent",
    fontSize: "1rem",
    fontWeight: "400",
    color: "black",
    outline: "none",
    borderBottom: "2px solid transparent",

    "&.active": {
      fontWeight: "bold",
      borderBottom: "3px solid #1971C2",
    },
  },
  watchlistButton: {
    cursor: "pointer",
    margin: "0.5rem 1rem",
    border: "none",
    backgroundColor: "transparent",
    fontSize: "1rem",
    fontWeight: "400",
    color: "black",
    outline: "none",
    borderBottom: "2px solid transparent",

    "&.active": {
      borderBottom: "3px solid #1971C2",
      fontWeight: "bold",
    },
  },
});

export const ListOptionsBar = () => {
  const styles = useStyles();
  const location = useLocation();

  return (
    <div className={styles.exploreWatchlistButtons}>
      <Link
        to="/"
        className={`${styles.exploreButton} ${
          location.pathname === "/" ? "active" : ""
        }`}
      >
        Explore
      </Link>
      <Link
        to="/watchlist"
        className={`${styles.watchlistButton} ${
          location.pathname === "/watchlist" ? "active" : ""
        }`}
      >
        Watchlist
      </Link>
    </div>
  );
};

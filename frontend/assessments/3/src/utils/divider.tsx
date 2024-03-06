import { createUseStyles } from 'react-jss';

interface OrangeBarProps {
  text: string;
}

const useStyles = createUseStyles({
  orangeBar: {
    backgroundColor: '#F08A5D',
    padding: '1.2rem',
    marginTop: "0.5rems",
    marginBottom: "0.5rem",
    color: 'white',
    textAlign: 'left',
    fontSize: '1rem',
    width: "100vw",
  },
});

const OrangeBar = ({ text}: OrangeBarProps ) => {
  const classes = useStyles();

  return (
    <div className={classes.orangeBar}>
      {text}
    </div>
  );
};

export default OrangeBar;

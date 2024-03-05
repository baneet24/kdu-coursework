import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../../public/calendar.png";
import { createUseStyles } from "react-jss";

const useStyles = createUseStyles({
  dates: {
    width: "0.5rem",
    height: "0.6rem",
  },

  dateSection: {
    display: "flex",
    marginBottom: "0.8rem",
    marginLeft: "0.7rem",
    alignItems: "center",
    marginTop: "0.8rem",
  },

  dateInput: {
    border: "none",
    outline: "none",
    width: "70%",
    fontSize: "0.5rem",
    backgroundColor: "#E9ECEF",
  },

  date: {
    border: "1px solid black",
    marginRight: "0.4rem",
    padding: "0.1rem 0.2rem",
  },
});

interface DateRangePickerProps {
  startDate: Date | null;
  endDate: Date | null;
  onStartDateChange: (date: Date | null) => void;
  onEndDateChange: (date: Date | null) => void;
}

const DateRangePicker: React.FC<DateRangePickerProps> = ({
  startDate,
  endDate,
  onStartDateChange,
  onEndDateChange,
}) => {
  const classes = useStyles();

  return (
    <div className={classes.dateSection}>
      <div className={classes.date}>
        <DatePicker
          selected={startDate}
          onChange={(date: Date | null) => onStartDateChange(date)}
          selectsStart
          startDate={startDate}
          endDate={endDate}
          dateFormat="MM/dd/yyyy"
          className={classes.dateInput}
        />
        <img src="calendar.png" alt="Calendar" className={classes.dates} />
      </div>

      <div className={classes.date}>
        <DatePicker
          selected={endDate}
          onChange={(date: Date | null) => onEndDateChange(date)}
          selectsEnd
          startDate={startDate}
          endDate={endDate}
          minDate={startDate}
          dateFormat="MM/dd/yyyy"
          className={classes.dateInput}
        />
        <img src="calendar.png" alt="Calendar" className={classes.dates} />
      </div>
    </div>
  );
};

export default DateRangePicker;

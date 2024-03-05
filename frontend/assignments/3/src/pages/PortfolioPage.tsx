import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../state/store/store";
import { createUseStyles } from "react-jss";
import { TransactionType } from "../Types/TransactionType";
import { Checkbox, IconButton, TextField } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import DateRangePicker from "../components/DateSection";
import Loader from "../components/Loader";

const useStyles = createUseStyles({
  container: {
    fontFamily: "Arial, sans-serif",
  },

  main: {
    display: "flex",
    marginTop: "1rem",

    "@media (max-width: 768px)": {
      flexDirection: "column",
    },
  },

  transactionContainer: {
    marginTop: "20px",
    width: "63%",
    "@media (max-width: 768px)": {
      width: "93%",
    },
  },
  dateHeading: {
    paddingBottom: "0.5rem",
    color: "#8B8B8C",
    fontSize: "1rem",
    borderBottom: "2px dotted #8B8B8C",
    marginTop: "2rem",
  },
  transactionItem: {
    minHeight: "4rem",
    listStyleType: "none",
    marginBottom: "10px",
    padding: "10px",
    borderBottom: "1px solid black",
    display: "flex",
    justifyContent: "space-around",
    alignItems: "center",
  },
  transactionInfo: {
    display: "flex",
    justifyContent: "space-between",
    width: "90%",
    flexWrap: "wrap",
  },

  name: {
    display: "flex",
    width: " 42%",
    justifyContent: "space-between",
  },

  price: {
    display: "flex",
    width: "40%",
    justifyContent: "space-between",
  },

  dot: {
    width: "10px",
    height: "10px",
    borderRadius: "50%",
  },
  dotPassed: {
    backgroundColor: "green",
  },
  dotFailed: {
    backgroundColor: "red",
  },

  filterContainer: {
    width: "32%",
    position: "sticky",
    top: "100px",
    "@media (max-width: 768px)": {
      display: "block",
      width: "100%",
      margin: "0",
      height: "4rem",
      overflowY: 'auto',
      '&:hover': {
        height: '22rem',
      },
    },
  },

  // filterBox: {
  //   position: "sticky",
  //   top: "6.5rem",
  //   marginLeft: "13%",
  //   width: "73%",
  //   minHeight: "22rem",
  //   backgroundColor: "#E9ECEF",
  //   borderRadius: "1.6rem",
  //   border: "2px solid black",
  //   "@media (max-width: 768px)": {
  //     display: "block",
  //     width: "100%",
  //     height: "1rem",
  //     overflowY: 'auto',
  //     margin: "0",
  //     borderRadius: "0rem",
  //   },
   //},
   filterBox: {
    position: "sticky",
    top: "6.5rem",
    marginLeft: "13%",
    width: "73%",
    minHeight: "22rem",
    backgroundColor: "#E9ECEF",
    borderRadius: "1.6rem",
    border: "2px solid black",
    
    "@media (max-width: 768px)": {
      display: "block",
      width: "100%",
      height: "1rem",
      overflowY: 'auto',
      margin: "0",
      borderRadius: "0rem",
      transition: 'height 0.3s ease-in-out',
      
    
      '&:hover': {
        height: '22rem', 
      },
    },
  },

  header: {
    display: "flex",
    justifyContent: "space-between",
    padding: "0.5rem 1rem",
    borderBottom: "2px solid Black",
  },

  clearButton: {
    border: "none",
    backgroundColor: "#E9ECEF",
    color: "#3682C9",
    fontSize: "0.9rem",
  },

  filtertext: {
    fontSize: "1rem",
  },

  searchBar: {
    display: "flex",
    padding: "0.5rem",
    borderBottom: "2px solid Black",
  },

  searchContainer: {
    display: "flex",
    alignItems: "center",
    outline: "none",
  },

  textField: {
    border: "none",
    outline: "none",
  },

  checkboxContainer: {
    display: "flex",
    alignItems: "left",
    marginTop: "5px",
    flexDirection: "column",
    borderBottom: "2px solid Black",
  },

  checkboxLabel: {
    textAlign: "top",
    marginLeft: "5px",
    fontSize: "0.9rem",
  },

  checks: {
    fontSize: "0.8rem",
  },

  dateContainer: {
    borderBottom: "2px solid Black",
  },

  listItems: {
    margin: "0.5rem 1rem",
    fontSize: "0.9rem",
    maxHeight: "16rem",
    overflowY: "auto",
    "&::-webkit-scrollbar": {
      width: "8px",
    },
    "&::-webkit-scrollbar-track": {
      background: "#f1f1f1",
    },
    "&::-webkit-scrollbar-thumb": {
      background: "#888",
      borderRadius: "7px",
    },

    "@media (max-width: 768px)": {
      height: "5rem",
    },
  },

  mainMobile: {
    flexDirection: "column",
  },

  filterContainerMobile: {
    position: "static",
    width: "100%",
  },

  filterBoxMobile: {
    position: "static",
    width: "100%",
    marginTop: "1rem",
  },
});

export const PortfolioPage = () => {
  const transactions = useSelector(
    (state: RootState) => state.transaction.transactions
  );

  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const status = useSelector((state: RootState) => state.stockMarket.state);
  const [searchQuery, setSearchQuery] = useState<string>("");

  const [groupedTransactions, setGroupedTransactions] = useState<
    Record<string, TransactionType[]>
  >({});

  const [filteredTransactions, setFilteredTransactions] = useState<
    TransactionType[]
  >([]);

  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);

  const [selectedStocks, setSelectedStocks] = useState<string[]>([]);

  const handleStartDateChange = (date: Date | null) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: Date | null) => {
    setEndDate(date);
  };

  const [showPassed, setShowPassed] = useState(false);
  const [showFailed, setShowFailed] = useState(false);

  const handlePassedChange = () => {
    setShowPassed(!showPassed);
  };

  const handleFailedChange = () => {
    setShowFailed(!showFailed);
  };

  const filterTransactions = () => {
    const filteredList = transactions.filter((transaction) => {
      const transactionDate = new Date(transaction.timestamp).getTime();

      if (!showPassed && !showFailed) {
        const startDateFilter =
          startDate === null || transactionDate >= startDate.getTime();

        const endDateFilter =
          endDate === null || transactionDate <= endDate.getTime();

        const searchFilter =
          !searchQuery ||
          transaction.stock_name
            .toLowerCase()
            .includes(searchQuery.toLowerCase());

        return startDateFilter && endDateFilter && searchFilter;
      }

      const statusFilter =
        (showPassed && transaction.status === "Passed") ||
        (showFailed && transaction.status === "Failed");

      const startDateFilter =
        startDate === null || transactionDate >= startDate.getTime();

      const endDateFilter =
        endDate === null || transactionDate <= endDate.getTime();

      const searchFilter =
        !searchQuery ||
        transaction.stock_name
          .toLowerCase()
          .includes(searchQuery.toLowerCase());
      if (
        transaction.stock_name.toLowerCase().includes(searchQuery.toLowerCase())
      )
        return statusFilter && startDateFilter && endDateFilter && searchFilter;
    });

    filteredList.sort(
      (a, b) =>
        new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime()
    );

    const groupedFilteredTransactions = filteredList.reduce(
      (acc, transaction) => {
        const date = new Date(transaction.timestamp).toLocaleDateString();
        if (!acc[date]) {
          acc[date] = [];
        }
        acc[date].push(transaction);
        return acc;
      },
      {} as Record<string, TransactionType[]>
    );

    setGroupedTransactions(groupedFilteredTransactions);

    setFilteredTransactions(filteredList);
  };

  const resetFilters = () => {
    setStartDate(null);
    setEndDate(null);
    setShowPassed(false);
    setShowFailed(false);
    setSearchQuery("");
    setSelectedStocks([]);
  };

  const handleStockSelection = (stockName: string) => {
    setSelectedStocks((prevSelectedStocks) => {
      if (prevSelectedStocks.includes(stockName)) {
        return prevSelectedStocks.filter(
          (selectedStock) => selectedStock !== stockName
        );
      } else {
        return [...prevSelectedStocks, stockName];
      }
    });
  };

  useEffect(() => {
    filterTransactions();
  }, [transactions, showPassed, showFailed, startDate, endDate, searchQuery]);

  const classes = useStyles();

  return (
    <div className={classes.container}>
      {status === "pending" ? (
        <Loader />
      ) : (
        <div className={classes.main}>
          <div className={classes.filterContainer}>
            <div className={classes.filterBox}>
              <div className={classes.header}>
                <p className={classes.filtertext}>Filters</p>
                <button className={classes.clearButton} onClick={resetFilters}>
                  Clear All
                </button>
              </div>

              <div className={classes.searchBar}>
                <div className={classes.searchContainer}>
                  <IconButton size="small" edge="start">
                    <SearchIcon />
                  </IconButton>
                  <TextField
                    label="Search for a Stock"
                    variant="outlined"
                    size="small"
                    fullWidth
                    className={classes.textField}
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                  />
                </div>
              </div>

              <div className={classes.dateContainer}>
                <DateRangePicker
                  startDate={startDate}
                  endDate={endDate}
                  onStartDateChange={handleStartDateChange}
                  onEndDateChange={handleEndDateChange}
                />
              </div>

              <div className={classes.checkboxContainer}>
                <div>
                  <Checkbox
                    checked={showPassed}
                    onChange={handlePassedChange}
                    color="primary"
                    className={classes.checks}
                  />
                  <span className={classes.checkboxLabel}>Passed</span>
                </div>

                <div>
                  <Checkbox
                    checked={showFailed}
                    onChange={handleFailedChange}
                    color="primary"
                    className={classes.checks}
                  />
                  <span className={classes.checkboxLabel}>Failed</span>
                </div>
              </div>

              <div className={classes.listItems}>
                {stocks.map((stockItem) => (
                  <div key={stockItem.stock_name}>
                    <input
                      type="checkbox"
                      checked={selectedStocks.includes(stockItem.stock_name)}
                      onChange={() =>
                        handleStockSelection(stockItem.stock_name)
                      }
                    />
                    {stockItem.stock_name}
                  </div>
                ))}
              </div>
            </div>
          </div>
          <div className={classes.transactionContainer}>
            {Object.keys(groupedTransactions).map((date) => (
              <div key={date}>
                <div className={classes.dateHeading}>{date}</div>
                <ul>
                  {groupedTransactions[date].map(
                    (transaction: TransactionType, index: number) => (
                      <li
                        key={index}
                        className={classes.transactionItem}
                        style={{
                          opacity:
                            selectedStocks.length === 0 ||
                            selectedStocks.includes(transaction.stock_name)
                              ? 1
                              : 0.5,
                          display:
                            filteredTransactions.length === 0 ||
                            filteredTransactions.includes(transaction)
                              ? "flex"
                              : "none",
                        }}
                      >
                        <div className={classes.transactionInfo}>
                          <div className={classes.name}>
                            <p>{transaction.stock_name}</p>
                            <p>{transaction.stock_symbol}</p>
                          </div>

                          <div className={classes.price}>
                            <p>&#8377;{transaction.transaction_price}</p>
                            <p>
                              {transaction.timestamp &&
                                transaction.timestamp
                                  .split("T")[1]
                                  .split(":")[0] +
                                  ":" +
                                  transaction.timestamp
                                    .split("T")[1]
                                    .split(":")[1]}
                            </p>
                          </div>
                        </div>
                        <div
                          className={`${classes.dot} ${
                            transaction.status === "Passed"
                              ? classes.dotPassed
                              : classes.dotFailed
                          }`}
                        ></div>
                      </li>
                    )
                  )}
                </ul>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

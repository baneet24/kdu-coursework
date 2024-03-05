import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../state/store/store";
import SnackBar from "../components/SnackBar";
import { getStocksData } from "../thunk/getStocks";
import { Box, Pagination, PaginationItem } from "@mui/material";
import { createUseStyles } from "react-jss";
import AddCircleRoundedIcon from "@mui/icons-material/AddCircleOutlineRounded";
import { addToWatchlist, removeFromWatchlist } from "../state/WatchListSlice";
import { StockType } from "../Types/StockType";
import CheckCircleRoundedIcon from "@mui/icons-material/CheckCircleRounded";
import CancelRoundedIcon from "@mui/icons-material/CancelRounded";
import { ListOptionsBar } from "../components/ListOptionsBar";
import Loader from "../components/Loader";
import { Link } from "react-router-dom";

const useStyles = createUseStyles({
  boxContainer: {
    minHeight: "73vh",
    margin: "0 10%",
    display: "flex",

    gap: 4,

    border: "2px solid grey",
    borderRadius: "2rem",
  },

  header: {
    display: "flex",
    padding: "1rem",
    paddingLeft: "2rem",
    justifyContent: "space-between",
    borderBottom: "2px solid grey",
  },

  priceheader: {
    display: "flex",
    justifyContent: "space-between",
    width: "35%",
    paddingRight: "2rem",
  },

  mainDiv: {
    width: "100%",
  },

  company: {
    width: "40%",
    color: "black",
  },

  stockItem: {
    display: "flex",
    justifyContent: "space-between",
    width: "92%",
    borderBottom: "2px solid #D3D4D5",
    marginLeft: "3%",
    padding: "0.8rem 0.1rem",
  },

  plus: {
    color: "#1971C2",
  },

  check: {
    color: "#1971C2",
  },

  cancelIcon: {
    color: "red",
    display: "none",
  },

  prices: {
    display: "flex",
    justifyContent: "space-between",
    flexWrap: "wrap",
    width: "30%",
  },

  watchlistButton: {
    backgroundColor: "white",
    border: "none",

    "&:hover": {
      "& $check": {
        display: "none",
      },
      "& $cancelIcon": {
        display: "block",
      },
    },
  },

  paginationContainer: {
    display: "flex",
    justifyContent: "center",
    paddingTop: "0.5rem",
  },
});

export const StockListPage = () => {
  const styles = useStyles();
  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const { watchlist } = useSelector((state: RootState) => state.watchlist);
  const status = useSelector((state: RootState) => state.stockMarket.state);
  const reducedispatch: AppDispatch = useDispatch();

  const itemsPerPage = 7;
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    reducedispatch(getStocksData());
  }, []);

  const handleAddToWatchlist = (stock: StockType) => {
    const isStockInWatchlist = watchlist.some(
      (item) => item.stock_name === stock.stock_name
    );

    if (isStockInWatchlist) {
      reducedispatch(removeFromWatchlist(stock));
    } else {
      reducedispatch(addToWatchlist(stock));
    }
  };

  const totalPages = Math.ceil(stocks.length / itemsPerPage);

  const handleChangePage = (
    event: React.ChangeEvent<unknown>,
    page: number
  ) => {
    setCurrentPage(page);
  };

  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;

  return (
    <div>
      <ListOptionsBar />

      <Box className={styles.boxContainer}>
        <div className={styles.mainDiv}>
          <div className={styles.header}>
            <div className={styles.company}>Company</div>
            <div className={styles.priceheader}>
              <div>Base Price</div>
              <div>Watchlist</div>
            </div>
          </div>
          {status === "pending" ? (
            <Loader />
          ) : (
            <>
              <ul>
                {stocks.slice(startIndex, endIndex).map((stock) => (
                  <li key={stock.stock_name}>
                    <div className={styles.stockItem}>
                      <Link
                        to={`/stockDetailPage/${stock.stock_name}`}
                        className={styles.company}
                      >
                        {stock.stock_name}
                      </Link>
                      <div className={styles.prices}>
                        <div>&#8377;{stock.base_price}</div>
                        <button
                          className={styles.watchlistButton}
                          style={{ backgroundColor: "white", border: "none" }}
                          onClick={() => handleAddToWatchlist(stock)}
                        >
                          {watchlist.some(
                            (item) => item.stock_name === stock.stock_name
                          ) ? (
                            <React.Fragment>
                              <CheckCircleRoundedIcon
                                className={styles.check}
                              />
                              <CancelRoundedIcon
                                className={styles.cancelIcon}
                              />
                            </React.Fragment>
                          ) : (
                            <AddCircleRoundedIcon className={styles.plus} />
                          )}
                        </button>
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
              <div className={styles.paginationContainer}>
                <Pagination
                  count={totalPages}
                  page={currentPage}
                  onChange={handleChangePage}
                  renderItem={(item) => (
                    <PaginationItem
                      component="div"
                      {...item}
                      style={{
                        color: "#1971C2",
                        border:
                          currentPage === item.page
                            ? "1px solid #1971C2"
                            : undefined,
                      }}
                    />
                  )}
                />
              </div>
            </>
          )}
        </div>
      </Box>
      <SnackBar />
    </div>
  );
};

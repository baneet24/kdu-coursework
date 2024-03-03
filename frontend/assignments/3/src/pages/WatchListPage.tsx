import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { DrawerAppBar } from "../components/ Navbar";
import { AppDispatch, RootState } from "../state/store/store";
import SnackBar from "../components/SnackBar";
import { getStocksData } from "../thunk/getStocks";
import { Box, Pagination, PaginationItem } from "@mui/material";
import { createUseStyles } from "react-jss";
import CheckCircleRoundedIcon from "@mui/icons-material/CheckCircleRounded";
import { StockType } from "../Types/StockType";
import { removeFromWatchlist } from "../state/WatchListSlice";
import CancelRoundedIcon from "@mui/icons-material/CancelRounded";
import { ListOptionsBar } from "../components/ListOptionsBar";

const useStyles = createUseStyles({
  boxContainer: {
    minHeight: "70vh",
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
  },

  stockItem: {
    display: "flex",
    justifyContent: "space-between",
    width: "92%",
    borderBottom: "2px solid #D3D4D5",
    marginLeft: "3%",
    padding: "0.7rem 0.1rem",
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

  prices: {
    display: "flex",
    justifyContent: "space-between",
    flexWrap: "wrap",
    width: "30%",
  },

  paginationContainer: {
    display: "flex",
    justifyContent: "center",
    paddingTop: "0.5rem",
  },
});

export const Watchlist = () => {
  const styles = useStyles();
  const { watchlist } = useSelector((state: RootState) => state.watchlist);
  const reducedispatch: AppDispatch = useDispatch();

  const itemsPerPage = 7;
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    reducedispatch(getStocksData());
  }, []);

  const handleRemoveFromWatchlist = (stock: StockType) => {
    reducedispatch(removeFromWatchlist(stock));
  };
  const totalPages = Math.ceil(watchlist.length / itemsPerPage);

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
      {/* <DrawerAppBar /> */}

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
          <ul>
            {watchlist.slice(startIndex, endIndex).map((stock: StockType) => (
              <li key={stock.stock_name}>
                <div className={styles.stockItem}>
                  <div className={styles.company}>{stock.stock_name}</div>
                  <div className={styles.prices}>
                    <div>&#8377;{stock.base_price}</div>
                    <button
                      style={{ backgroundColor: "white", border: "none" }}
                      className={styles.watchlistButton}
                      onClick={() => handleRemoveFromWatchlist(stock)}
                    >
                      <CheckCircleRoundedIcon className={styles.check} />
                      <CancelRoundedIcon className={styles.cancelIcon} />
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
        </div>
      </Box>

      <SnackBar />
    </div>
  );
};

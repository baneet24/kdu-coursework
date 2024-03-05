import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "../state/store/store";
import { useParams } from "react-router-dom";
import { createUseStyles } from "react-jss";

import uparrow from "../../public/uparrow.png";
import downarrow from "../../public/downarrow.png";

import {
  Select,
  MenuItem,
  FormControl,
  ListSubheader,
  SelectChangeEvent,
  Button,
  List,
  ListItem,
} from "@mui/material";
import { BarGraph } from "../components/BarGraph";
import { addTransaction, updateWallet } from "../state/TransactionHistorySlice";
import { StockType } from "../Types/StockType";
import { HistoryType } from "../Types/HistoryType";

const useStyles = createUseStyles({
  transactionSection: {
    display: "flex",
  },
  menuPaper: {
    maxHeight: 230,
    width: 100,
  },

  info: {
    marginTop: "2rem",
    marginLeft: "3%",
    display: "flex",
    width: "72vw",

    justifyContent: "space-around",
  },

  symbolWithBackground: {
    padding: "5px",
    borderRadius: "1px",
    marginRight: "8%",
  },

  symbolBackgroundBlue: {
    backgroundColor: "#1976D2",
    color: "white",
  },

  symbolBackgroundRed: {
    backgroundColor: "red",
    color: "white",
  },

  selectField: {
    height: "100px",
    overflowY: "auto",
    width: "22%",
  },

  select: {
    fontSize: "0.8rem",
    minWidth: "5rem",
  },

  dropItem: {
    height: "35px",
    fontSize: "0.67rem",
  },

  pricetext: {
    fontSize: "1.3rem",
  },

  priceField: {
    display: "flex",
    fontSize: "0.9rem",
    alignItems: "center",

    padding: "0.1rem 0.1rem 0.1rem 1rem",
    width: "15rem",
    height: "68px",
    justifyContent: "space-around",
    border: "1px solid grey",
    borderRadius: "0.2rem",
  },
  priceData: {
    display: "flex",
    alignItems: "center",
    fontSize: "1.2rem",
    width: "2rem",
  },

  priceValue: {},

  image: {
    paddingTop: "0.1rem",
    height: "1.6rem",
  },

  percent: {
    fontSize: "0.8rem",
    marginTop: "0.8rem",
    paddingLeft: "2rem",
  },

  quantitySection: {
    fontSize: "0.8rem",
    padding: "1.4rem",
    width: "12rem",
    height: "68px",
    textAlign: "center",
    border: "1px solid grey",
    borderRadius: "0.2rem",
  },

  buyBtn: {
    fontSize: "0.9rem",
    backgroundColor: "#b2f2bb",
    color: "#2f9e44",
    padding: "0.7rem",
    width: "5rem",
    textAlign: "center",
    border: "1px solid #2f9e44",
    height: "68px",
  },
  sellBtn: {
    fontSize: "0.9rem",
    backgroundColor: "#ffc9c9",
    color: "#e03131",
    padding: "0.7rem",
    width: "5rem",
    textAlign: "center",
    border: "1px solid #e03131",
    height: "68px",
  },

  transactionHistory: {
    marginTop: "2rem",
    marginLeft: "3%",
    marginRight: "2%",
    width: "72vw",
    border: "1px solid black",
    height: "16rem",
    padding: "0.6rem",
    borderRadius: "0.2rem",
    overflowY: "auto",
    "&::-webkit-scrollbar": {
      width: "8px",
    },
    "&::-webkit-scrollbar-thumb": {
      background: "#888",
      borderRadius: "5px",
      height: " 10px",
    },
  },

  transactionItem: {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    border: "1px solid grey",
    borderRadius: "0.6rem",
    padding: "0.5rem",
    margin: "0.5rem 0",
  },

  transactionFailed: {
    color: "red",
  },

  transactionPassed: {
    color: "green",
  },

  transactionBuy: {
    color: "green",
  },

  transactionSell: {
    color: "red",
  },

  quantity: {
    fontSize: "1rem",
    color: "black",
  },
  time: {
    fontSize: "0.6rem",
    color: "black",
  },
});

export const StockDetailPage = () => {
  const styles = useStyles();
  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const { name } = useParams();
  const [selectedStock, setSelectedStock] = useState(name);
  const wallet = useSelector((state: RootState) => state.transaction.wallet);
  const basePrice =
    stocks.find((stock: StockType) => stock.stock_name === name)?.base_price ||
    0;
  const [currentRandomValue, setCurrentRandomValue] =
    useState<number>(basePrice);
  const [previousRandomValue, setPreviousRandomValue] =
    useState<number>(basePrice);
  const [percentageChange, setPercentageChange] = useState<number>(0);
  const [quantity, setQuantity] = useState<number>(0);
  const [history, setHistory] = useState<HistoryType[]>([]);

  const reducedispatch: AppDispatch = useDispatch();

  const handleChange = (event: SelectChangeEvent<string>) => {
    setSelectedStock(event.target.value);
  };

  const handleInputChange = (event: { target: { value: string } }) => {
    const inputValue = parseInt(event.target.value);

    setQuantity(inputValue);
  };

  const handleRandomValueChange = (value: number) => {
    setPreviousRandomValue(currentRandomValue);
    setCurrentRandomValue(value);

    const change = ((value - currentRandomValue) / currentRandomValue) * 100;
    setPercentageChange(change);
  };

  const buyStock = () => {
    if (quantity > 0) {
      const stock = stocks.find(
        (stock: StockType) => stock.stock_name === selectedStock
      );
      const totalCost = quantity * currentRandomValue;

      if (wallet >= totalCost && stock) {
        reducedispatch(updateWallet(wallet - totalCost));

        const HistoryItem = {
          stock_name: stock.stock_name,
          stock_quantity: quantity,
          timestamp: new Date().toISOString(),
          type: "Buy",
          status: "Passed",
        };

        const transaction = {
          stock_name: stock.stock_name,
          stock_symbol: stock.stock_symbol,
          transaction_price: totalCost,
          timestamp: new Date().toISOString(),
          status: "Passed",
        };

        reducedispatch(addTransaction(transaction));
        setHistory((prevHistory) => [...prevHistory, HistoryItem]);

        setQuantity(0);
      } else if (stock) {
        const transaction = {
          stock_name: stock.stock_name,
          stock_symbol: stock.stock_symbol,
          transaction_price: totalCost,
          timestamp: new Date().toISOString(),
          status: "Failed",
        };

        reducedispatch(addTransaction(transaction));

        setQuantity(0);
      }
    }
  };

  const sellStock = () => {
    if (quantity > 0) {
      const stock = stocks.find(
        (stock: StockType) => stock.stock_name === selectedStock
      );
      const totalCost = quantity * currentRandomValue;

      reducedispatch(updateWallet(wallet + totalCost));

      if (stock) {
        const HistoryItem = {
          stock_name: stock.stock_name,
          stock_quantity: quantity,
          timestamp: new Date().toISOString(),
          type: "Sell",
          status: "Passed",
        };

        const transaction = {
          stock_name: stock.stock_name,
          stock_symbol: stock.stock_symbol,
          transaction_price: totalCost,
          timestamp: new Date().toISOString(),
          status: "Passed",
        };

        reducedispatch(addTransaction(transaction));
        setHistory((prevHistory) => [...prevHistory, HistoryItem]);
        setQuantity(0);
      }
    }
  };

  return (
    <div className={styles.transactionSection}>
      <div>
        <div className={styles.info}>
          <FormControl className={styles.selectField}>
            <Select
              value={selectedStock}
              onChange={handleChange}
              MenuProps={{ classes: { paper: styles.menuPaper } }}
              className={styles.select}
              displayEmpty
            >
              <ListSubheader>Stocks</ListSubheader>
              {stocks.map((stock) => (
                <MenuItem
                  key={stock.stock_name}
                  value={stock.stock_name}
                  className={styles.dropItem}
                >
                  <div className={styles.symbolWithBackground}>
                    <span
                      className={`${styles.symbolWithBackground} ${
                        stock.stock_symbol.includes("A")
                          ? styles.symbolBackgroundBlue
                          : styles.symbolBackgroundRed
                      }`}
                    >
                      {stock.stock_symbol}
                    </span>
                    {` ${stock.stock_name}`}
                  </div>
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <div className={styles.priceField}>
            <div className={styles.pricetext}>Price </div>
            <div className={styles.priceData}>
              <div
                className={styles.priceValue}
                style={{
                  color:
                    currentRandomValue > previousRandomValue
                      ? "green"
                      : currentRandomValue < previousRandomValue
                      ? "red"
                      : "black",
                }}
              >
                {currentRandomValue}
              </div>
              <div>
                {currentRandomValue < previousRandomValue ? (
                  <img src={downarrow} alt="down" className={styles.image} />
                ) : (
                  <img src={uparrow} alt="up" className={styles.image} />
                )}
              </div>
            </div>

            <div className={styles.percent}>
              {" "}
              {percentageChange.toFixed(2)}%
            </div>
          </div>

          <input
            type="number"
            placeholder="Enter QTY"
            className={styles.quantitySection}
            value={quantity === 0 ? "" : quantity}
            onChange={handleInputChange}
          />
          <Button className={styles.buyBtn} onClick={buyStock}>
            Buy
          </Button>
          <Button className={styles.sellBtn} onClick={sellStock}>
            Sell
          </Button>
        </div>
        <BarGraph onRandomValueChange={handleRandomValueChange} />
      </div>

      <div className={styles.transactionHistory}>
        <h2 style={{ fontWeight: "600" }}>History</h2>
        <List>
          {history.map((transaction, index) => (
            <ListItem
              key={index}
              className={`${styles.transactionItem} ${
                transaction.status === "Passed"
                  ? styles.transactionPassed
                  : styles.transactionFailed
              }`}
            >
              <div>
                <div className={styles.quantity}>
                  {transaction.stock_quantity} stocks
                </div>

                <div className={styles.time}>Fri, {transaction.timestamp}</div>
              </div>

              <div
                className={`${
                  transaction.type === "Sell"
                    ? styles.transactionSell
                    : styles.transactionBuy
                }`}
              >
                {transaction.type}
              </div>
            </ListItem>
          ))}
        </List>
      </div>
    </div>
  );
};

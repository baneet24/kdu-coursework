import { useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../state/store/store";
import { useParams } from "react-router-dom";
import { createUseStyles } from "react-jss";
import {
  Select,
  MenuItem,
  FormControl,
  ListSubheader,
  SelectChangeEvent,
} from "@mui/material";
import {BarGraph} from "../components/BarGraph";

const useStyles = createUseStyles({
  menuPaper: {
    maxHeight: 230,
    width: 100,
  },

  info: {
    marginTop: "2rem",
    marginLeft: "3%",
  },

  symbolWithBackground: {
    padding: "5px",
    borderRadius: "1px",
    marginRight: "8%",
  },

  symbolBackgroundBlue: {
    backgroundColor: "#1976D2",
    color: 'white'
  },

  symbolBackgroundRed: {
    backgroundColor: "red",
    color: 'white'
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
});

export const StockDetailPage = () => {
  const styles = useStyles();
  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const { name } = useParams();
  const [selectedStock, setSelectedStock] = useState(name);

  const handleChange = (event: SelectChangeEvent<string>) => {
    setSelectedStock(event.target.value);
  };

  return (
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
      </div>
      <BarGraph/>
    </div>
  );
};




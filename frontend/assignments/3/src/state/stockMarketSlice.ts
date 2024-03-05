import { createSlice } from "@reduxjs/toolkit";
import { getStocksData } from "../thunk/getStocks";
import { StockType } from "../Types/StockType";

enum States {
  fulfilled = "fulfilled",
  pending = "pending",
  rejected = "rejected",
}

interface StockDataState {
  stocks: StockType[];
  state: States;
  error?: Error;
}

const initialState: StockDataState = {
  stocks: [],
  state: States.pending,
};

const stockMarketSlice = createSlice({
  name: "stockDetailsData",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getStocksData.fulfilled, (state, action) => {
        state.state = States.fulfilled;
        const sortedList = action.payload
          .slice()
          .sort((a: StockType, b: StockType) =>
            a.stock_name.localeCompare(b.stock_name)
          );
        state.stocks = sortedList;
      })
      .addCase(getStocksData.pending, (state) => {
        state.state = States.pending;
      })
      .addCase(getStocksData.rejected, (state) => {
        state.state = States.rejected;
      });
  },
});

export const stockMarketReducer = stockMarketSlice.reducer;

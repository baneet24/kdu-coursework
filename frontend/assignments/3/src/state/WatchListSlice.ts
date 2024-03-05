import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { StockType } from "../Types/StockType";

interface WatchlistState {
  watchlist: StockType[];
}

const initialState: WatchlistState = {
  watchlist: [],
};

const watchlistSlice = createSlice({
  name: "watchlist",
  initialState: initialState,
  reducers: {
    addToWatchlist: (state, action: PayloadAction<StockType>) => {
      state.watchlist.push(action.payload);
    },

    removeFromWatchlist: (state, action: PayloadAction<StockType>) => {
      state.watchlist = state.watchlist.filter(
        (item) => item.stock_name !== action.payload.stock_name
      );
    },
  },
});

export const { addToWatchlist, removeFromWatchlist } = watchlistSlice.actions;
export const watchlistReducer = watchlistSlice.reducer;

import { configureStore } from "@reduxjs/toolkit";
import { stockMarketReducer } from "../stockMarketSlice";
import snackBarReducer from "../SnackBarSlice";
import { watchlistReducer } from "../WatchListSlice";

export const store = configureStore({
  reducer: {
    stockMarket: stockMarketReducer,
    watchlist: watchlistReducer,
    snackBar: snackBarReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

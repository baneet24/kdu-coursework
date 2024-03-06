import { configureStore } from "@reduxjs/toolkit";
import { roomDataReducer } from "../RoomDataSlice";
import { selectedAddOnReducer } from "../AddOnSlice";
import snackBarReducer from "../SnackBarSlice";

export const store = configureStore({
  reducer: {
    roomData: roomDataReducer,
    snackBar: snackBarReducer,
    AddOnsChosen: selectedAddOnReducer
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

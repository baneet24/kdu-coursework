import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getRoomData } from "../thunk/getrooms";
import { AlertColor } from "@mui/material";

interface ISnackBar {
  show: boolean;
  message: string;
  variant: AlertColor;
}

const initialState: ISnackBar = {
  show: false,
  message: "",
  variant: "error",
};

export const snackBarSlice = createSlice({
  name: "snackbar",
  initialState: initialState,
  reducers: {
    setShow: (state, action: PayloadAction<boolean>) =>{
      state.show = action.payload
    }
  },

  extraReducers(builder) {
    builder
    .addCase(getRoomData.fulfilled, (state) =>{
        state.message = "Rooms data loaded successfully"
        state.show = true
        state.variant = "success"
    })
    .addCase(getRoomData.rejected, (state) => {
        state.message = "Oops!! rooms data cannot be loaded"
        state.show = true
        state.variant = "error"
    });
  },
});

const snackBarReducer = snackBarSlice.reducer;
export const { setShow } = snackBarSlice.actions
export default snackBarReducer;
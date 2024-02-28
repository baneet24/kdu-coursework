import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getProducts } from "../thunk/getProducts";

interface ISnackBar {
  show: boolean;
  message: string;
  variant: string;
}

const initialState: ISnackBar = {
  show: false,
  message: "",
  variant: "",
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
    .addCase(getProducts.fulfilled, (state) =>{
        state.message = "Products loaded successfully"
        state.show = true
        state.variant = "success"
    })
    .addCase(getProducts.rejected, (state) => {
        state.message = "Oops!! Products cannot be loaded"
        state.show = true
        state.variant = "error"
    });
  },
});

const snackBarReducer = snackBarSlice.reducer;
export const { setShow } = snackBarSlice.actions
export default snackBarReducer;
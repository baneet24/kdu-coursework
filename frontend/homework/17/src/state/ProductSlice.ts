import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { getProducts } from "../thunk/getProducts";
import { IProduct } from "../Types/IProduct";

enum States {
  fulfilled = "fulfilled",
  pending = "pending",
  rejected = "rejected",
}

export interface IProductList {
  productList: IProduct[];
  state: States;
}

const initialState: IProductList = {
  productList: [],
  state: States.pending,
};

const productListSlice = createSlice({
  name: "productList",
  initialState: initialState,
  reducers: {
  },
  extraReducers(builder) {
    builder.addCase(getProducts.pending, (state) => {
        state.state = States.pending;
    }).addCase(getProducts.fulfilled, (state, action: PayloadAction<IProduct[]>) => {
        state.state = States.fulfilled;
        state.productList = action.payload;
    }).addCase(getProducts.rejected, (state, action: PayloadAction<any>) => {
        state.state = States.rejected;
        state.productList = action.payload;
    })
  },
});

const productListReducer = productListSlice.reducer;
export default productListReducer;

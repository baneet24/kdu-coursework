import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { getTransactionsData } from "../thunk/getTransactions";
import { TransactionType } from "../Types/TransactionType";

enum States {
  fulfilled = "fulfilled",
  pending = "pending",
  rejected = "rejected",
}

interface TransactionDataState {
  transactions: TransactionType[];
  wallet: number;
  state: States;
  error?: Error;
}

const initialState: TransactionDataState = {
  transactions: [],
  wallet: 10000,
  state: States.pending,
};

const transactionDataSlice = createSlice({
  name: "transactionDetailsData",
  initialState: initialState,
  reducers: {
    updateWallet: (state, action: PayloadAction<number>) => {
      state.wallet = action.payload;
    },
    addTransaction: (state, action: PayloadAction<TransactionType>) => {
      state.transactions.push(action.payload);
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        getTransactionsData.fulfilled,
        (state, action: PayloadAction<TransactionType[]>) => {
          state.state = States.fulfilled;
          state.transactions = action.payload;
        }
      )
      .addCase(getTransactionsData.pending, (state) => {
        state.state = States.pending;
      })
      .addCase(getTransactionsData.rejected, (state) => {
        state.state = States.rejected;
      });
  },
});
export const { updateWallet, addTransaction } = transactionDataSlice.actions;
export const transactionDataReducer = transactionDataSlice.reducer;

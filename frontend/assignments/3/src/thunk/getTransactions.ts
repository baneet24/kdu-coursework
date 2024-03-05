import { createAsyncThunk } from "@reduxjs/toolkit";

export const getTransactionsData = createAsyncThunk(
  "getTransactionsData",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
      );
      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error("Cannot get transactions data");
    }
  }
);

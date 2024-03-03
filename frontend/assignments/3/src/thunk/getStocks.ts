import { createAsyncThunk } from "@reduxjs/toolkit";

export const getStocksData = createAsyncThunk("getStocksData", async () => {
  try {
    await new Promise((loading) => setTimeout(loading, 2000));

    const response = await fetch(
      "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"
    );
    const data = await response.json();
    return data;
  } catch (error) {
    throw new Error("Cannot get stock data");
  }
});

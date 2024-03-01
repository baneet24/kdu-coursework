import { createAsyncThunk } from "@reduxjs/toolkit";

export const getRoomData = createAsyncThunk(
  "roomData/fetchRoomData",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
      );
      const data = await response.json();
     for (const key in data){
        const itemsArr = data[key];
        console.log(itemsArr);
        return itemsArr;
     }
    
    } catch (error) {
      throw new Error("cannot get room data");
    }
  }
);



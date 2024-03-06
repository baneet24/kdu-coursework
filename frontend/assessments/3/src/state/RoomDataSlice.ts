import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getRoomData } from "../thunk/getrooms";
import { RoomType } from "../types/RoomType";

enum States {
  fulfilled = "fulfilled",
  pending = "pending",
  rejected = "rejected",
}

interface RoomDataState {
  rooms: RoomType[];
  selectedRoomId: number;
  state: States;
}

const initialState: RoomDataState = {
  rooms: [],
  selectedRoomId: -1,
  state: States.pending,
};

const roomDataSlice = createSlice({
  name: "roomDetailsData",
  initialState: initialState,
  reducers: {
    setRoom: (state, action: PayloadAction<number>) => {
      state.selectedRoomId = action.payload;
      state.rooms.forEach((room) => {
        if (room.id === action.payload) room.isselected = true;
      });
      console.log("room set");
    },

    unsetRoom: (state, action: PayloadAction<number>) => {
      state.selectedRoomId = action.payload;
      state.rooms.forEach((room) => {
        if (room.id === action.payload) room.isselected = false;
      });
      console.log("room unset");
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        getRoomData.fulfilled,
        (state, action: PayloadAction<RoomType[]>) => {
          state.state = States.fulfilled;
          state.rooms = action.payload;
        }
      )
      .addCase(getRoomData.pending, (state) => {
        state.state = States.pending;
      })
      .addCase(getRoomData.rejected, (state) => {
        state.state = States.rejected;
      });
  },
});

export const { setRoom, unsetRoom } = roomDataSlice.actions;
export const roomDataReducer = roomDataSlice.reducer;

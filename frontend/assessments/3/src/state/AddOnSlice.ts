import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface SelectedAddOnsState {
  AddOnsChosen: string[];
  ChosenAddOnId: number;
}

const initialState: SelectedAddOnsState = {
  AddOnsChosen: [],
  ChosenAddOnId: -1,
};

const selectedAddOnsSlice = createSlice({
  name: "AddOnChosen",
  initialState,
  reducers: {
    deleteAllAddOns: (state) => {
      state.AddOnsChosen = [];
    },

    setAllAddOns: (state, action: PayloadAction<string[]>) => {
      for (const addOnVal in action.payload)
        state.AddOnsChosen.push(addOnVal);
    },
    setAddOn: (state, action: PayloadAction<string>) => {
      state.AddOnsChosen.push(action.payload);
    },
    desetAddOn: (state, action: PayloadAction<string>) => {
      state.AddOnsChosen = state.AddOnsChosen.filter(
        (addOnVal) => addOnVal !== action.payload
      );
    },
  },
});

export const { setAddOn, desetAddOn, deleteAllAddOns, setAllAddOns } =
  selectedAddOnsSlice.actions;

export const selectedAddOnReducer = selectedAddOnsSlice.reducer;

import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../state/store/store";
import { getRoomData } from "../thunk/getrooms";
import { setRoom, unsetRoom } from "../state/RoomDataSlice";
import {
  setAddOn,
  desetAddOn,
  deleteAllAddOns,
} from "../state/AddOnSlice";
import OrangeBar from "../utils/divider";
import DateRangePicker from "./datesSection";
import { createUseStyles } from 'react-jss';
import SnackBar from "./snackbar/snackbar";

const useStyles = createUseStyles({
  submitButton: {
    backgroundColor: '#F08A5D',
    marginTop: '0.5rem',
    padding: '0.6rem 1.6rem',
    color: 'white',
    fontSize: '1.4rem',
    border: 'none',
    borderRadius: '0.1rem'
  },
});

export const HotelPage = () => {
  const styles = useStyles();
  const reducedispatch: AppDispatch = useDispatch();
  const { rooms, selectedRoomId, state } = useSelector(
    (state: RootState) => state.roomData
  );
  const selectedAddOns = useSelector(
    (state: RootState) => state.AddOnsChosen.AddOnsChosen
  );

  useEffect(() => {
    reducedispatch(getRoomData());
  }, []);
  
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [costWithoutGST, setCostWithoutGST] = useState<number>(0);

  const handleStartDateChange = (date: Date | null) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: Date | null) => {
    setEndDate(date);
  };


  const handleRoomTypeSet = (roomId: number) => {
    rooms.forEach((room) => {
      if(room.id === roomId)
      if(room.isselected === true){
        reducedispatch(unsetRoom(roomId));
        console.log("checking unset");
        return;
      }
    });
    reducedispatch(setRoom(roomId));
    reducedispatch(deleteAllAddOns());
  };

  const handlePreferenceSelect = (name: string) => {
    if (!selectedAddOns.includes(name)) {
      reducedispatch(setAddOn(name));
    } else {
      reducedispatch(desetAddOn(name));
    }
  };

  const handleCalculateCost = () => {
    const chosenRoom = rooms.find((room) => room.id === selectedRoomId);
    if (!chosenRoom) return;

    const roomCost: number = chosenRoom.costPerNight;

    const days = Math.ceil(
      (endDate!.getTime() - startDate!.getTime()) / (1000 * 3600 * 24)
    );

  
    const perNightCost: number = +roomCost;
    const cost: number = perNightCost * days;
    console.log(cost);
    setCostWithoutGST(cost);
  };


  return (
    <div>
      <OrangeBar text="Select Room Type" />
      <div>
        <ul>
          {rooms.map((room) => (
            <button
              key={room.id}
              onClick={() => handleRoomTypeSet(room.id)}
              style={{
                color:
                  selectedRoomId === room.id ? "#F08A5D" : "black",
                  borderColor: 
                  selectedRoomId === room.id ?
                  "#F08A5D": "black" ,
              }}
            >
              {room.name}
            </button>
          ))}
        </ul>
      </div>

      <OrangeBar text="Select Dates" />
      <div>
        <DateRangePicker
          startDate={startDate}
          endDate={endDate}
          onStartDateChange={handleStartDateChange}
          onEndDateChange={handleEndDateChange}
        />
      </div>

      <OrangeBar text="Select additonal add ons/preferences" />
      <div>
        {selectedRoomId && (
          <div>
            {rooms
              .find((room) => room.id === selectedRoomId)
              ?.addOns.map((addOn) => (
                <div key = {addOn.name}>
                  <button
                    onClick={() => handlePreferenceSelect(addOn.name)}
                    style={{
                      backgroundColor: selectedAddOns.includes(addOn.name)
                        ? "orange"
                        : "white",
                
                      margin: "5px",
                    }}
                  >
                    {addOn.name} - {addOn.cost}
                  </button>
                </div>
              ))}
          </div>
        )}
      </div>

      <div>
      <p>Cost + 18%GST = {costWithoutGST}</p>
        <button onClick={handleCalculateCost} className={`submit-btn ${styles.submitButton}`}> Submit</button>
       
      </div>
      <SnackBar />
    </div>
  );
};

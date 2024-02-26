import { useEffect, useRef } from "react";

export const MutableValues = () => {
  const inputVal = useRef<number>(3);

  useEffect(() => {
    inputVal.current = 5;
    console.log("Value is:", inputVal.current);
  }, []);

  return (
    <div>
      <h1>Mutable Values</h1>
      <p>Mutable Value: {inputVal.current}</p>
    </div>
  );
};

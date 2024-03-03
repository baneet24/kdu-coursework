import { useState, useEffect, useRef } from "react";

export const Timer = () => {
  const [seconds, setSeconds] = useState<number>(0);
  const intervalTime = useRef<number | null>(null);

  const startTimer = () => {
    intervalTime.current = window.setInterval(() => {
      setSeconds((prevSeconds) => prevSeconds + 1);
    }, 1000);
  };

  const stopTimer = () => {
    if (intervalTime.current !== null) {
      clearInterval(intervalTime.current);
    }
  };

  useEffect(() => {
    return () => {
      stopTimer();
    };
  }, []);

  return (
    <div>
      <h1>Timer: {seconds} seconds</h1>
      <button onClick={startTimer}>Start Timer</button>
      <button onClick={stopTimer}>Stop Timer</button>
    </div>
  );
};

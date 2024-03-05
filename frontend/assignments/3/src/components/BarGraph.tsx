import React, { useState, useEffect } from "react";
import {
  Chart,
  BarController,
  BarElement,
  LinearScale,
  Title,
  CategoryScale,
} from "chart.js";
import { Bar } from "react-chartjs-2";
import { createUseStyles } from "react-jss";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../state/store/store";

Chart.register(BarController, BarElement, LinearScale, Title, CategoryScale);

interface BarGraphProps {
  onRandomValueChange: (value: number) => void;
}

const useStyles = createUseStyles({
  graphContainer: {
    maxHeight: 700,
    maxWidth: 1350,
    marginLeft: 26,
  },
  currentValueContainer: {
    textAlign: "center",
    marginTop: 20,
    fontSize: 18,
    fontWeight: "bold",
  },
});

export const BarGraph: React.FC<BarGraphProps> = ({ onRandomValueChange }) => {
  const { name } = useParams();

  const styles = useStyles();
  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const basePrice =
    stocks.find((stock) => stock.stock_name === name)?.base_price || 0;
  const [chartData, setChartData] = useState(generateInitialData());
  const [currentRandomValue, setCurrentRandomValue] = useState<number>(0);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setChartData((prevData) => {
        if (prevData.every((value) => value !== 0)) {
          const randomValue = generateRandomValue();
          setCurrentRandomValue(randomValue);
          onRandomValueChange(randomValue);
          return [...prevData, randomValue];
        } else {
          const newData = [...prevData];
          const randomValue = generateRandomValue();
          setCurrentRandomValue(randomValue);
          onRandomValueChange(randomValue);
          newData[newData.findIndex((value) => value === 0)] = randomValue;
          return newData;
        }
      });
    }, 5000);

    return () => clearInterval(intervalId);
  }, [chartData, onRandomValueChange]);

  function generateInitialData() {
    return [basePrice, ...Array(40).fill(0)];
  }

  function generateRandomValue() {
    return Math.floor(Math.random() * 10000);
  }

  const data = {
    labels: Array.from(
      { length: chartData.length },
      (_, index) => `${index * 5}`
    ),
    datasets: [
      {
        label: "Random Data",
        data: chartData.slice(1),
        backgroundColor: chartData.slice(1).map((value, index) => {
          if (value > chartData[index]) {
            return "#b2f2bb";
          } else if (value < chartData[index]) {
            return "#ffc9c9";
          } else {
            return "#b2f2bb";
          }
        }),
        borderWidth: 1,
        barThickness: 30,
      },
    ],
  };

  const options = {
    scales: {
      y: {
        beginAtZero: true,
      },
      x: {
        beginAtZero: true,
        ticks: {
          stepSize: 100,
        },
      },
    },
    plugins: {
      legend: {
        display: true,
        position: "top",
      },
    },
    indexAxis: "x",
    barThickness: "flex",
    barPercentage: 0.5,
    categoryPercentage: 1.0,
  };

  return (
    <div>
      <Bar data={data} options={options} className={styles.graphContainer} />
    </div>
  );
};

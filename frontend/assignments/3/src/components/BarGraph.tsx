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

export const BarGraph: React.FC = () => {
  const { name } = useParams();
  console.log("gogogogo", name);
  const styles = useStyles();
  const stocks = useSelector((state: RootState) => state.stockMarket.stocks);
  const basePrice =
    stocks.find((stock) => stock.stock_name === name)?.base_price || 50;
  const [chartData, setChartData] = useState(generateInitialData());
  const [currentRandomValue, setCurrentRandomValue] = useState<number>(0);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setChartData((prevData) => {
        if (prevData.every((value) => value !== 0)) {
          const randomValue = generateRandomValue();
          setCurrentRandomValue(randomValue);
          return [...prevData, randomValue];
        } else {
          const newData = [...prevData];
          const randomValue = generateRandomValue();
          setCurrentRandomValue(randomValue);
          newData[newData.findIndex((value) => value === 0)] = randomValue;
          return newData;
        }
      });
    }, 5000);

    return () => clearInterval(intervalId);
  }, [chartData]);

  function generateInitialData() {
    return [
      basePrice,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
    ];
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
    barPercentage: 0.8,
  };

  return (
    <div>
      <div className={styles.currentValueContainer}>
        Current Random Value: {currentRandomValue}
      </div>
      <Bar data={data} options={options} className={styles.graphContainer} />
    </div>
  );
};

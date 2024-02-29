import { useEffect } from "react";
import { IProduct } from "../../Types/IProduct";
import { getProducts } from "../../thunk/getProducts";
import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "../../state/store/store";
import Loader from "../loader/Loader";
import SnackBar from "../snackbar/snackbar";

const styles:{[key: string]: React.CSSProperties} = {
  main: {
    backgroundColor: "rgb(242, 242, 242)",
    width: "100%",
    height: "100%",
  },

  listGrid: {
    display: "grid",
    gridTemplateColumns: "repeat(4, 1fr)",
    gap: "20px",
    paddingTop: "5em",
  },

  headingKDU: {
    color: "black",
    fontWeight: "600",
    fontSize: "3rem",
    paddingLeft: "40rem",
    paddingTop: "10rem",
  },

  headingMarket: {
    color: "#28296C",
    fontWeight: "600",
    fontSize: "3rem",
  },

  listItem: {
    backgroundColor: "white",

  },

  priceStyle: {
    color: "blue"
  }
};

const ProductListPage = () => {
  const productsList = useSelector(
    (state: RootState) => state.productList.productList
  );

  const status = useSelector((state: RootState) => state.productList.state);
  const reduxDispatch: AppDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(getProducts());
  }, []);

  return (
    <div>
      {status === "pending" ? (
        <Loader />
      ) : status === "fulfilled" ? (
        <div style={styles.main}>
          <span style={styles.headingKDU}>KDU </span>
          <span style={styles.headingMarket}>MARKETPLACE</span>
          <ul style={styles.listGrid}>
            {productsList.map((product: IProduct) => (
              <li key={product.id} style = {styles.listItem}>
                <img
                  src={product.image}
                  alt={product.title}
                  style={{ width: "60%", maxWidth: "300px", display: "flex", justifyContent: "center" }}
                />
                <p>{product.title}</p>
                <p style={styles.priceStyle}>Price: ${product.price}</p>
              </li>
            ))}
          </ul>
          <SnackBar />
        </div>
      ) : (
        <SnackBar />
      )}
    </div>
  );
};

export default ProductListPage;

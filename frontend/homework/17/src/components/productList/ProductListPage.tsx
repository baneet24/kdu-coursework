import { useEffect } from "react";
import { IProduct } from "../../Types/IProduct";
import { getProducts } from "../../thunk/getProducts";
import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "../../state/store/store";
import Loader from "../loader/Loader";
import SnackBar from "../snackbar/snackbar";

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
        <ul>
          <h2>KDU MarketPlace</h2>
          {productsList.map((product: IProduct) => (
            <li key={product.id}>
              <img
                src={product.image}
                alt={product.title}
                style={{ width: "100%", maxWidth: "300px" }}
              />
              <h3>{product.title}</h3>
              <p>Price: ${product.price}</p>
              <p>Category: {product.category}</p>
              <p>Description: {product.description}</p>
              <p>
                Rating: {product.rating.rate} ({product.rating.count} reviews)
              </p>
            </li>
          ))}
          <SnackBar />
        </ul>
      ) : (
        <SnackBar/>
      )}
    </div>
  );
};

export default ProductListPage;

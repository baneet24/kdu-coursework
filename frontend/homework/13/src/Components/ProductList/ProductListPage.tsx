// ProductListPage.ts
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { useProductsContext } from "../../Util/ProductContext";
import { Header } from "./Header/Header";

function ProductListPage() {
  const { productsList, setProductsList } = useProductsContext();

  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((Response) => Response.json())
      .then((data) => setProductsList(data));
  });

  return (
    <div>
<Header/>
      <h2>Product List</h2>
      <ul>
        {productsList.map((product) => (
          <li key={product.id}>
            <Link to={`/product/${product.id}`}>{product.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductListPage;
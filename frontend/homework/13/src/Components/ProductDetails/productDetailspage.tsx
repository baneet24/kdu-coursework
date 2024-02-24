import { useParams } from "react-router-dom";
import { useProductsContext } from "../../Util/ProductContext";
import { IProduct } from "../../Types/IProduct";
import { useEffect, useState } from "react";

function ProductDetailsPage() {
  const { productId } = useParams();
  const { productsList } = useProductsContext();

  const [selectedProduct, setSelectedProduct] = useState<IProduct | undefined>(
    undefined
  );

  useEffect(() => {
    const existingProduct = productsList.find(
      (product) => String(product.id) === productId
    );
    if (existingProduct) {
      setSelectedProduct(existingProduct);
    } else {
      fetch(`https://fakestoreapi.com/products/${productId}`)
        .then((response) => response.json())
        .then((data) => setSelectedProduct(data))
        .catch((error) =>
          console.error("Error fetching product details:", error)
        );
    }
  }, []);

  if (!selectedProduct) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      
      <img src={selectedProduct.image} alt={selectedProduct.title} />
      <h3>{selectedProduct.title}</h3>
      <p>Price: ${selectedProduct.price}</p>
      <p>Category: ${selectedProduct.category}</p>
      <p>Description: {selectedProduct.description}</p>
      <p>
        Rating: {selectedProduct.rating.rate} ({selectedProduct.rating.count}{" "}
        reviews)
      </p>
    </div>
  );
}

export default ProductDetailsPage;

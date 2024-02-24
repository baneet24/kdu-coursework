import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";
import ProductListPage from "./Components/ProductList/ProductListPage";
import ProductDetailsPage from "./Components/ProductDetails/productDetailspage";
import { ProductsProvider } from "./Util/ProductContext";
import { NotFoundPage } from "./Components/NotFound/NotFoundPage";

function App() {
  return (
    <BrowserRouter>
    <ProductsProvider>
        <Routes>
          <Route path = "/" element = {<ProductListPage />} />
          <Route path="/product/:productId" element={<ProductDetailsPage />} />
          <Route path = "*" element = {<NotFoundPage />} />
        </Routes>
        </ProductsProvider>
     </BrowserRouter>
  );
}

export default App;

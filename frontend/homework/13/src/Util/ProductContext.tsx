import React, { createContext, useContext, useState } from "react";
import { IProduct } from "../Types/IProduct";

interface ProductsContextProps {
  children: React.ReactNode;
}

interface IProductsListContext {
  productsList: IProduct[];
  setProductsList: React.Dispatch<React.SetStateAction<IProduct[]>>;
  searchItem: string;
  setSearchItem: React.Dispatch<React.SetStateAction<string>>;
}

const ProductsContextVal: IProductsListContext = {
  productsList: [],
  setProductsList: () => {},
  searchItem: "",
  setSearchItem: () => {},
};

const ProductsContext = createContext<IProductsListContext>(ProductsContextVal);

export const ProductsProvider = ({ children }: ProductsContextProps) => {
  const [productsList, setProductsList] = useState<IProduct[]>([]);
  const [searchItem, setSearchItem] = useState<string>("");

  const contextValue: IProductsListContext = {
    productsList,
    setProductsList,
    searchItem,
    setSearchItem,
  };

  return (
    <ProductsContext.Provider value={contextValue}>
      {children}
    </ProductsContext.Provider>
  );
};

export const useProductsContext = (): IProductsListContext => {
  const context = useContext(ProductsContext);
  return context;
};

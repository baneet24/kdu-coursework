//API response format
import { IRecipe } from "./recipe";
export interface ResponseObject {
    recipes: IRecipe[];
    total: number;
    skip: number;
    limit: number;
  }
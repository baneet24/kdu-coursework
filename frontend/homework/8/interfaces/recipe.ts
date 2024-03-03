//Output format
export interface IRecipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    caloriesPerServing: number;
  }
  
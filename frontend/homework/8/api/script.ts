import { IRecipe } from "../interfaces/recipe";
import { ResponseObject } from "../interfaces/response";

export class RecipeClass {
  public resp: ResponseObject;
  constructor() {
    this.resp = {} as ResponseObject;
  }

  //fetch recipes from API and store them
  async fetchRecipesFromAPI(): Promise<void> {
    try {
      const response = await fetch("https://dummyjson.com/recipes");

      if (!response.ok) {
        throw new Error(response.statusText);
      }

      this.resp = (await response.json()) as ResponseObject;
    } catch (error) {
      console.error("Error fetching recipes:", error);
    }
  }


  //search based on a query
  public searchRecipes(query: string): Promise<void> {
    const searchUrl = `https://dummyjson.com/recipes/search?q=${query}`;

    return fetch(searchUrl)
      .then(async (response) => {
        if (!response.ok) {
          throw new Error(response.statusText);
        }

        this.resp = (await response.json()) as ResponseObject;
      })
      .catch((error) => {
        console.error("Error fetching recipes:", error);
      });
  }


  //Function to print All recipes in required format
  public printAllRecipes(): void {
    this.resp.recipes.forEach((recipe) => {
      console.log(`
              Image: ${recipe.image}
              Name: ${recipe.name}
              Rating: ${recipe.rating}
              Cuisine: ${recipe.cuisine}
              Ingredients: ${recipe.ingredients.join(", ")}
              Difficulty: ${recipe.difficulty}
              Time taken: ${recipe.prepTimeMinutes + recipe.cookTimeMinutes} 
              Calorie Count: ${recipe.caloriesPerServing}
          `);
    });
  }
}

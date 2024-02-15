import { RecipeClass } from "./api/script";

//Instance of classs
const recipeClass = new RecipeClass();


//ensures that call to fetch function is executed first and then next are exeuted
recipeClass.fetchRecipesFromAPI()
    .then(() => {
        recipeClass.printAllRecipes();
    })

// //query based search
console.log("Search based result:")
recipeClass.searchRecipes("Chicken")
  .then(() => {
    recipeClass.printAllRecipes();
  })
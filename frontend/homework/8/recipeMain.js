"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var script_1 = require("./api/script");
//Instance of classs
var recipeClass = new script_1.RecipeClass();
//ensures that call to fetch function is executed first and then next are exeuted
recipeClass.fetchRecipesFromAPI()
    .then(function () {
    recipeClass.printAllRecipes();
});
// //query based search
console.log("Search based result:");
recipeClass.searchRecipes("Chicken")
    .then(function () {
    recipeClass.printAllRecipes();
});

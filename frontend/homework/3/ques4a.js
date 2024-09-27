// Function to convert a JSON string to an object
function convertToUppercaseExceptEmail(input) {
  const jsonObject = JSON.parse(input);
  const transformedObject = {};

  // Iterate through each key-value pair in the original object
  for (const [key, value] of Object.entries(jsonObject)) {
    if (key === "email") {
      transformedObject[key] = value;
    } else {
      transformedObject[key] =
        typeof value === "string" ? value.toUpperCase() : value;
    }
  }

  // Return the transformed object
  return transformedObject;
}

function main() {
  const userInput = prompt("Enter a JSON string:");

  const outputObject = convertToUppercaseExceptEmail(userInput);
  console.log(outputObject);
}

main();

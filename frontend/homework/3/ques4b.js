// Function to transform a JavaScript object, excluding the 'email' property, into a JSON string
function jsonToString(obj) {
  var transformedObj = {};

  for (const [key, value] of Object.entries(obj)) {
    if (key !== "email") {
      transformedObj[key] = value;
    }
  }

  // Convert the transformed object to a JSON string
  const stringResponse = JSON.stringify(transformedObj);

  return stringResponse;
}

function main() {
  // Sample object with user information
  const obj = {
    firstName: "ALEX",
    lastName: "HUNTER",
    email: "alex@yahoo.com",
    age: 24,
    city: "LONDON",
    country: "ENGLAND",
  };

  console.log(jsonToString(obj));
}

main();

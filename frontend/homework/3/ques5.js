// Function to output keys and corresponding values of a nested object
function outputKeysAndValues(obj) {
  console.log("All the keys:");

  for (const key in obj) {
    if (obj.hasOwnProperty(key)) {
      console.log(`key is: ${key}`);
      console.log("value is: ");
      console.log(obj[key]);
    }
  }
}

// Main function with keys and values of a nested object
function main() {
  const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
      country: "Spain",
      city: "Barcelona",
    },
    careerInfo: {
      fcBarcelona: {
        appearances: 780,
        goals: {
          premierLeagueGoals: 590,
          championsLeagueGoals: 50,
        },
      },
    },
  };

  outputKeysAndValues(player);
}

main();

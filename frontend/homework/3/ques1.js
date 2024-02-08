// Function to calculate tip and total amount based on the bill value
function calculateBillTip(bill) {
  var tip;

  if (bill < 50) {
    tip = 0.2 * bill;
  } else if (bill >= 50 && bill <= 200) {
    tip = 0.15 * bill;
  } else {
    tip = 0.1 * bill;
  }

  // Return an object with tip and total amount
  return {
    tip: tip,
    totalAmount: bill + tip,
  };
}

function main() {
  var bills = [140, 45, 280];
  var tips = [];
  var totalAmounts = [];

  // Iterate through each bill in the array
  for (var i = 0; i < bills.length; i++) {
    var result = calculateBillTip(bills[i]);
    tips.push(result.tip);
    totalAmounts.push(result.totalAmount);
  }

  // Display the tips and total amounts
  console.log("Tips:", tips);
  console.log("Total Amounts:", totalAmounts);
}

main();

const shoe1 = { type: 'Sports', color: 'Black', size: '10', price: 80 };
const shoe2 = { type: 'Casual', color: 'Brown', size: '9', price: 65 };

const shirt1 = { type: 'Polo', color: 'Blue', size: 'M', price: 35 };
const shirt2 = { type: 'Formal', color: 'White', size: 'L', price: 50 };
const shirt3 = { type: 'Casual', color: 'Blue', size: 'S', price: 40 };

const shoes = [shoe1, shoe2];
const shirts = [shirt1, shirt2, shirt3];


const warehouse = [...shoes, ...shirts];


const totalWorth = warehouse.reduce((sum, product) => sum + product.price, 0);

// Sort the warehouse array in descending order of prices
warehouse.sort((a, b) => b.price - a.price);

// Display warehouse products which are blue in color
const blueProducts = warehouse.filter(product => product.color === 'Blue');

console.log("Warehouse:", warehouse);
console.log("Total Worth of Products:", totalWorth);
console.log("Warehouse Products with Blue Color:", blueProducts);

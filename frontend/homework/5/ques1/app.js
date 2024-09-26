const os = require('os');
const http = require('http');
const fs = require('fs');
const getSystemInfo = require('./getSystemInfo')
const writeJson = require('./writeJson')

const info = getSystemInfo();

const filePath = 'storeData.json';
writeJson(info, filePath);


// Create HTTP server
http.createServer((req, res) => {

    fs.readFile(filePath, (err, data) => {
        const startMessage = `Hello, my name is Baneet!\nHere is my system information:\n${data}`;
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(startMessage);
    });
  })
.listen(3000, () => {
  console.log(`Server running...`);
});

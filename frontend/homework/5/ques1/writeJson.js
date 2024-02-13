const fs = require('fs');

function writeJson(jsonData, filePath) {
  fs.writeFileSync(filePath, JSON.stringify(jsonData, null, 2));
}

module.exports = writeJson;

const extractFileInfo = require('./extractFileInfo');

function processFilePaths(filePaths) {
  return filePaths.map((filePath) => extractFileInfo(filePath));
}

module.exports = processFilePaths;

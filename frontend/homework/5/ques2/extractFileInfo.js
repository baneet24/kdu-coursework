
const path = require('path');

function extractFileInfo(filePath) {
  return {
    extension: path.extname(filePath),     //file extension
    baseName: path.basename(filePath),     //file name
    directory: path.dirname(filePath),     //file directory
  };
}

module.exports = extractFileInfo;

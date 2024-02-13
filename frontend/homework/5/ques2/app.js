const processFilePaths = require('./processFilePaths');

//list of file paths
const filePaths = [
  'dir1/dir2/file1.txt',
  'dir1/dir3/file2.txt',
  'dir1/dir3/file3.md',
  'dir4/file4.jpg',
  'dir4/file5.pdf',
];

//Calling the processFilePaths function
const result = processFilePaths(filePaths);
console.log(result);

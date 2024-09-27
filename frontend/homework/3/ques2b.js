function convertToCodedString(originalString) {
  originalString = originalString.trim(); //remove leading and trailing spaces

  // Map to replace certain characters with their coded equivalents
  const replaceMap = {
    a: "4",
    e: "3",
    i: "1",
    o: "0",
    s: "5",
  };

  let codedString = "";

  for (let character of originalString) {
    codedString += replaceMap[character] || character;
  }

  return codedString;
}

function main() {
  console.log(convertToCodedString("javascript is cool  "));
  console.log(convertToCodedString("programming is fun"));
  console.log(convertToCodedString("  become a coder"));
}

main();

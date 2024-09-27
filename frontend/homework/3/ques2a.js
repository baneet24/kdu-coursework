function main(){
    // Array representing the days of the week with extra spaces
    var days = ["Sunday   ","   Monday  ","  Tuesday","Wednesday  ","  Thursday   ","   Friday", "Saturday    "]

    var abbreviatedDays = [];

    // Iterate through each day in the days array
    for(var i = 0; i < days.length; i++){
        var result = convertToAbbreviaton(days[i]);
        abbreviatedDays.push(result);
    }

    console.log(abbreviatedDays);
}

function convertToAbbreviaton(day) {
    return day.trim().toUpperCase().slice(0,3);  // Trim, convert to uppercase, and slice to get the first three characters
}


main();

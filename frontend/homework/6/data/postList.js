const { v4: uuidv4 } = require('uuid');


//list of posts containing content, tags
let postList = [
    {
        id: uuidv4(),
        name: "baneet",
        content: "It is a good day",
        tags: "#niceDay"
    },

    {
        id: uuidv4(),
        name: "joe",
        content: "Lot of work to do!",
        tags: "#busy"
    },

    {
        id: uuidv4(),
        name: "kale",
        content: "What an amazing episode it was!!",
        tags: "#naruto#anime"
    }
]

module.exports = postList;
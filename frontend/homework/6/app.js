const express = require("express");
const path=require('path')
const app = express();


app.use(express.json())
app.use('/api/posts',require('./routes/api/posts'))
//using port 3001
app.listen(3001,()=>{
    console.log("Server started on 3001");
})
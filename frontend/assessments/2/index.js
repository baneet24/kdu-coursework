const express = require("express");
const http = require("http");
const cors = require("cors");
const path=require('path')
const socketIo = require("socket.io");
const app = express();


app.use(express.json())
app.use('/api/stocks',require('./routes/api/prices'))


io.on("connection", (socket) => {
    console.log("connection created..");
  
    socket.on("message", (payload) => {
      console.log("Payload", payload);
      io.except(socket.id).emit("new-message", payload);
    });
  });
  
  server.listen(3000, () => {
    console.log("server started..");
  });

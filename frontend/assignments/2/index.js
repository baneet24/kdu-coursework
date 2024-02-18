const express = require("express");
const app = express();
const http = require('http')
const path = require('path');
const socketIo = require("socket.io");

const cors = require("cors");

const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
      origin: "http://127.0.0.1:5500",
    },
  });


  const activeUsers = {}; 


app.use(cors());
app.use(express.json());
app.use(express.static("public"));
//app.use(express.static("login"))


app.use("/api/user/login", require("./routes/api/loginAPI"));
app.use("/api/posts", require("./routes/api/postsAPI"));



io.on("connection", (socket) => {
    console.log("A user connected");

    // Listen for a login event from the client
    
    socket.on("login", (username) => {
     // console.log(username);
      activeUsers[username] = socket;
      for (const userId in activeUsers) {
        console.log(userId);
      }
      
        // Broadcast the user's name to all connected clients
        io.emit("userLoggedIn", username);
    });

    // Handle disconnect event
    socket.on("disconnect", () => {
      const disconnectedUser = Object.keys(activeUsers).find(key => activeUsers[key] === socket);
      if (disconnectedUser) {
          console.log(`${disconnectedUser} disconnected`);
          delete activeUsers[disconnectedUser];
      }
    });
});



server.listen(3001, () => {
  console.log("Server started on 3001");
});

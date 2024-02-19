const express = require("express");
const app = express();
const http = require("http");
const path = require("path");
const socketIo = require("socket.io");

const activeUsers = {};
const cors = require("cors");

const server = http.createServer(app);
const io = new socketIo.Server(server, {
  cors: {
    origin: "http://127.0.0.1:5501",
  },
});

app.use(cors());
app.use(express.json());
app.use(express.static("public"));

app.use("/api/user/login", require("./routes/api/loginAPI"));
app.use("/api/posts", require("./routes/api/postsAPI"));

io.on("connection", (socket) => {
  console.log("A user connected");


  socket.on("login", (username) => {
    if (!activeUsers[username]) {
      activeUsers[username] = socket.id;
    }
    io.emit("userAdded", username);
  });


  socket.on("disconnect", () => {
    const disconnectedUser = Object.keys(activeUsers).find(
      (key) => activeUsers[key] === socket.id
    );
    if (disconnectedUser) {
      console.log(`${disconnectedUser} disconnected`);
      delete activeUsers[disconnectedUser];
    }
  });

  socket.on("message", (data) => {
    const { to, message } = data;
    const senderUsername = Object.keys(activeUsers).find(
      (key) => activeUsers[key] === socket.id
    );

    if (activeUsers[to]) {
      io.to(activeUsers[to]).emit("privateMessage", {
        from: senderUsername,
        message,
      });
      console.log(`${to} received message`);
    } else {
      console.log(
        `${to} is not online...`
      );
    }
  });
});

app.get("/getActiveUsers", (req, res) => {
  console.log(activeUsers, "activeUsers");
  res.status(200).send(activeUsers);
});

server.listen(3002, () => {
  console.log("Server started on 3002");
});

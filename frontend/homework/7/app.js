const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");

const app = express();

const server = http.createServer(app);
const io = new socketIo.Server(server, {
  cors: {
    origin: "http://127.0.0.1:5500",
  },
});

app.use(cors());
app.use(express.json());

io.on("connection", (socket) => {
  console.log("connection created..");

  socket.on("message", (payload) => {
    console.log("Payload", payload);
    //sends paylload to each client connected except the one that sends data
    io.except(socket.id).emit("new-message", payload);
  });
});

server.listen(3000, () => {
  console.log("server started..");
});

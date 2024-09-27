const socket = io("http://localhost:3000");
const messageInput = document.getElementById("msgInput");
const sendButton = document.getElementById("sendMessage");
const messageOutput = document.getElementById("messages");
const toggleModeButton = document.getElementById("toggleMode");

//add message to DOM
function addMessage(from, message) {
  const iconElement = document.createElement("p");
  iconElement.classList.add("icon");
  iconElement.innerText = from;
  const element = document.createElement("p");
  element.classList.add("msg");
  element.innerText = message;
  const messageElement = document.createElement("div");
  messageElement.classList.add("message");
  messageElement.appendChild(iconElement);
  messageElement.appendChild(element);
  messageOutput.appendChild(messageElement);
}

//sending messages
sendButton.addEventListener("click", () => {
  const message = messageInput.value;
  socket.emit("message", message);
  addMessage("Y", message);
});

socket.on("new-message", (message) => {
  addMessage("U", message);
});


//for dark and bright themes
toggleModeButton.addEventListener("click", () => {
  var bgColor = document.body.style.backgroundColor;
  const inputcontainer = document.getElementById("msg-container");
  const inputElement = document.getElementById("msgInput");
  const msgElement = document.getElementsByClassName("msg");
  if (bgColor === "white") {
    document.body.style.backgroundColor = "#111B21";
    document.body.style.color = "#e7e9ea";
    inputcontainer.style.backgroundColor = "#202c33";
    inputElement.style.backgroundColor = "#202c33";
    inputElement.style.color = "#e7e9ea";
    for (el of msgElement) {
      el.style.backgroundColor = "#202c33";
    }
  } else {
    document.body.style.backgroundColor = "white";
    document.body.style.color = "#111B21";
    inputcontainer.style.backgroundColor = "#F0F0F0";
    inputElement.style.backgroundColor = "#F0F0F0";
    inputElement.style.color = "black";
    for (el of msgElement) {
      el.style.backgroundColor = "#F0F0F0";
    }
    msgElement.style.backgroundColor = "#F0F0F0";
  }
});

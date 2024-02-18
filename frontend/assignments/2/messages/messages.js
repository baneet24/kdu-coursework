var interactionContainer = document.getElementsByClassName(
  "interaction-section"
)[0];
var tweetBoxContainer = document.getElementsByClassName("tweet-box")[0];
var postsContainer = document.getElementsByClassName("posts")[0];
var msgContainer = document.getElementsByClassName("message-section")[0];
var username = url.searchParams.get("username");






// socket.on("userLoggedIn", (loggedInUsername) => {
//   // Update the UI to show the name of the user who just logged in
//   if (loggedInUsername !== username) {
//       const newUserElement = document.createElement("div");
//       newUserElement.textContent = `${loggedInUsername} just logged in`;
//       newUserElement.style.color = "white";
//       interactionContainer.appendChild(newUserElement);

//   }
// });


document
  .getElementById("messages-container")
  .addEventListener("click", function () {
    //socket.emit("login", username);
    
    // if (msgContainer.style.display === "none") {
      document.getElementById("home-text").style.fontWeight = "400";
      document.getElementById("message-text").style.fontWeight = "600";
      msgContainer.textContent = "Messages";
      msgContainer.style.display = "block";
      tweetBoxContainer.style.display = "none";
      postsContainer.style.display = "none";
    //}
  });

document.getElementById("home-container").addEventListener("click", function () {
    document.getElementById("home-text").style.fontWeight = "600";
    document.getElementById("message-text").style.fontWeight = "400";
    tweetBoxContainer.style.display = "block";
    postsContainer.style.display = "block";
    var msgContainer = document.getElementsByClassName("message-section")[0];
    msgContainer.style.display = "none";

});


document.getElementById("home-text").style.fontWeight = "400";
document.getElementById("message-text").style.fontWeight = "600";
msgContainer.textContent = "Messages";
msgContainer.style.display = "block";
tweetBoxContainer.style.display = "none";
postsContainer.style.display = "none";
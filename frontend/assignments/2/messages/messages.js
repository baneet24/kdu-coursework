var interactionContainer = document.getElementsByClassName(
  "interaction-section"
)[0];
var tweetBoxContainer = document.getElementsByClassName("tweet-box")[0];
var postsContainer = document.getElementsByClassName("posts")[0];
var msgContainer = document.getElementsByClassName("message-section")[0];
var username = url.searchParams.get("username");



document
  .getElementById("messages-container")
  .addEventListener("click", function () {
    
      document.getElementById("home-text").style.fontWeight = "400";
      document.getElementById("message-text").style.fontWeight = "600";
      msgContainer.textContent = "Messages";
      msgContainer.style.display = "block";
      tweetBoxContainer.style.display = "none";
      postsContainer.style.display = "none";

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
msgContainer.style.display = "none";
tweetBoxContainer.style.display = "block";
postsContainer.style.display = "block";
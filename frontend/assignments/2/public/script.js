var url = new URL(window.location.href);

const socket = io("http://localhost:3002");

var username = url.searchParams.get("username");
socket.on("userAdded", function () {
  getActiveUsers();
});

setTimeout(() => {
  socket.emit("login", username);
}, 1000);

function createUserDiv(username) {
  const userDiv = document.createElement("div");
  userDiv.classList.add("user-name-msg");
  userDiv.textContent = username;

  let profilePicture = document.createElement("div");
  profilePicture.classList.add("user-msg-img");
  profilePicture.innerText = username[0].toUpperCase();
  profilePicture.width = 50;
  profilePicture.height = 50;
  profilePicture.style.color = "white";
  profilePicture.style.backgroundColor = "#ef6c00";

  let userParentDiv = document.createElement("div");
  userParentDiv.classList.add("msg-section-item");

  userParentDiv.appendChild(profilePicture);
  userParentDiv.appendChild(userDiv);
  userParentDiv.style.display = "flex";

  userDiv.style.padding = "10px";
  userDiv.style.margin = "10px 0";
  userDiv.style.backgroundColor = "#16181C";
  userDiv.style.borderRadius = "5px";
  userDiv.style.cursor = "pointer";

  userDiv.addEventListener("click", () => {
    initiateChat(username);
  });

  return userParentDiv;
}

async function getActiveUsers() {
  try {
    console.log("in client");
    const response = await fetch("http://localhost:3002/getActiveUsers");
    const activeUsers = await response.json();

    console.log(activeUsers);

  
    const messageSection = document.querySelector(".message-section");

    while (messageSection.firstChild) {
      messageSection.removeChild(messageSection.firstChild);
    }

    for (const username in activeUsers) {
      const userParentDiv = createUserDiv(username);

      messageSection.appendChild(userParentDiv);
    }

    return activeUsers;
  } catch (error) {
    console.error("Error fetching active users:", error);
  }
}

async function initiateChat(username) {
  try {
    
    const activeUserslist = await getActiveUsers();
    console.log("lll");
    console.log(activeUserslist);
    const socketId = activeUserslist[username];

    if (socketId) {
      // Now you have the socket ID, you can use it to send private messages
      const message = prompt(`Enter your message to ${username}`);
      if (message) {
        socket.emit("privateMessage", { to: username, message });
      }
    } else {
      console.error(`No socket ID found for ${username}`);
    }
  } catch (error) {
    console.error("Error:", error);
  }
}


socket.emit("login", username);

window.addEventListener("scroll", function () {
  if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
    callApiWithCounter();
  }
});

function addPost(postText, type, username) {
  if (postText.trim() !== "") {
    var postContainer = document.createElement("div");
    postContainer.classList.add("post");

    var profilePicture = document.createElement("div");
    profilePicture.classList.add("post-img");
    profilePicture.innerText = username[0].toUpperCase();
    profilePicture.width = 50;
    profilePicture.height = 50;

    // Create a paragraph element for the post text
    var postElement = document.createElement("p");
    postElement.textContent = postText;
    postElement.classList.add("post-text-item");

    //upper container
    var upperContainer = document.createElement("div");
    upperContainer.classList.add("upper-post-section");
    upperContainer.classList.add("post-text-item");
    //childs of upperContainer
    var nameElement = document.createElement("div");
    nameElement.id = "user-name";
    nameElement.textContent = username;
    upperContainer.appendChild(nameElement);

    //child id of upperContainer
    var idElement = document.createElement("div");
    idElement.id = "user-id";
    idElement.textContent = "@" + username + ". 1s";
    upperContainer.appendChild(idElement);

    var textContainer = document.createElement("div");
    textContainer.classList.add("post-text");

    var svgContainer = document.createElement("div");
    svgContainer.classList.add("svg-container");

    var subSvgContainer = document.createElement("div");
    subSvgContainer.classList.add("sub-svg-container");

    //making svgs
    // Creating an SVG element-1
    var svgElement = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement.setAttribute("viewBox", "0 0 24 24");
    svgElement.setAttribute("aria-hidden", "true");
    svgElement.classList.add(
      "r-4qtqp9",
      "r-yyyyoo",
      "r-dnmrzs",
      "r-bnwqim",
      "r-1plcrui",
      "r-lrvibr",
      "r-1xvli5t",
      "r-1hdv0qi"
    );

    var gElement = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement.setAttribute(
      "d",
      "M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756"
    );

    gElement.appendChild(pathElement);
    svgElement.appendChild(gElement);
    subSvgContainer.appendChild(svgElement);

    //creating an SVG element-2
    // Creating an SVG element
    var svgElement = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement.setAttribute("viewBox", "0 0 24 24");
    svgElement.setAttribute("aria-hidden", "true");
    svgElement.classList.add(
      "r-4qtqp9",
      "r-yyyyoo",
      "r-dnmrzs",
      "r-bnwqim",
      "r-1plcrui",
      "r-lrvibr",
      "r-1xvli5t",
      "r-1hdv0qi"
    );

    var gElement = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement.setAttribute(
      "d",
      "M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"
    );

    gElement.appendChild(pathElement);
    svgElement.appendChild(gElement);
    subSvgContainer.appendChild(svgElement);

    //svg-3 -- like post
    // Creating an SVG element
    var svgElement2 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement2.setAttribute("viewBox", "0 0 24 24");
    svgElement2.setAttribute("aria-hidden", "true");
    svgElement2.classList.add(
      "r-4qtqp9",
      "r-yyyyoo",
      "r-dnmrzs",
      "r-bnwqim",
      "r-1plcrui",
      "r-lrvibr",
      "r-1xvli5t",
      "r-1hdv0qi",
      "like-post"
    );

    svgElement2.id = "like-post";
    var gElement2 = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement2 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement2.setAttribute(
      "d",
      "M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
    );
    gElement2.appendChild(pathElement2);
    svgElement2.appendChild(gElement2);
    subSvgContainer.appendChild(svgElement2);

    //svg-4
    // Creating an SVG element
    var svgElement3 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement3.setAttribute("viewBox", "0 0 24 24");
    svgElement3.setAttribute("aria-hidden", "true");
    svgElement3.classList.add(
      "r-4qtqp9",
      "r-yyyyoo",
      "r-dnmrzs",
      "r-bnwqim",
      "r-1plcrui",
      "r-lrvibr",
      "r-1xvli5t",
      "r-1hdv0qi"
    );

    var gElement3 = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement3 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement3.setAttribute(
      "d",
      "M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"
    );

    gElement3.appendChild(pathElement3);
    svgElement3.appendChild(gElement3);

    subSvgContainer.appendChild(svgElement3);

    //svg-5
    // Creating an SVG element
    // Creating an SVG element
    var svgElement5 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement5.setAttribute("viewBox", "0 0 24 24");
    svgElement5.setAttribute("aria-hidden", "true");
    svgElement5.classList.add("bookmark");
    var gElement5 = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement5 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement5.setAttribute(
      "d",
      "M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11"
    );

    gElement5.appendChild(pathElement5);
    svgElement5.appendChild(gElement5);
    subSvgContainer.appendChild(svgElement5);

    //svg-6
    // Creating an SVG element
    // Creating an SVG element
    var svgElement6 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "svg"
    );
    svgElement6.setAttribute("viewBox", "0 0 24 24");
    svgElement6.setAttribute("aria-hidden", "true");
    svgElement6.classList.add(
      "r-4qtqp9",
      "r-yyyyoo",
      "r-dnmrzs",
      "r-bnwqim",
      "r-1plcrui",
      "r-lrvibr",
      "r-1xvli5t",
      "r-1hdv0qi"
    );

    var gElement6 = document.createElementNS("http://www.w3.org/2000/svg", "g");
    var pathElement6 = document.createElementNS(
      "http://www.w3.org/2000/svg",
      "path"
    );
    pathElement6.setAttribute(
      "d",
      "M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2"
    );

    gElement6.appendChild(pathElement6);
    svgElement6.appendChild(gElement6);

    svgContainer.appendChild(subSvgContainer);
    svgContainer.appendChild(svgElement6);

    svgElement2.addEventListener("click", function () {
      var currentColor = svgElement2.getAttribute("fill");
      var numberContainer = textContainer.querySelector("span");

      if (currentColor === null) {
        // Liked state
        svgElement2.innerHTML = `
            <svg viewBox="0 0 24 24" aria-hidden="true" class="unlike-post" style="fill: #F91880;">
              <g>
                <path d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
              </g>
            </svg>
          `;
        svgElement2.setAttribute("fill", "pink");

        if (numberContainer) {
          numberContainer.textContent = "1";
        } else {
          numberContainer = document.createElement("span");
          numberContainer.classList.add("likes-count");
          numberContainer.textContent = "1";
          textContainer.appendChild(numberContainer);
        }
      } else {
        // Unliked state
        svgElement2.innerHTML = `
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-post" id="like-post">
              <g>
                <path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
              </g>
            </svg>
          `;
        svgElement2.removeAttribute("fill");

        if (numberContainer) {
          textContainer.removeChild(numberContainer);
        }
      }
    });

    // adding to text
    textContainer.appendChild(upperContainer);
    textContainer.appendChild(postElement);
    textContainer.appendChild(svgContainer);

    //adding to post
    postContainer.appendChild(profilePicture);
    postContainer.appendChild(textContainer);

    // Get the post section
    var postSection = document.getElementById("posts");

    // Add new post container to the post section
    if (postSection.firstChild && type == "new") {
      postSection.insertBefore(postContainer, postSection.firstChild);
    } else {
      postSection.appendChild(postContainer);
    }

    // postSection.appendChild(postContainer);
    document.getElementById("post-input").value = "";
  }
}

let counter = 0;
function callApiWithCounter() {
  fetch(`http://localhost:3002/api/posts/${counter}/${5}`)
    .then((response) => response.json())
    .then((posts) => {
      console.log(posts);
      posts.forEach((post) => {
        addPost(post.content, "scroll", post.username);
      });
    })
    .catch((error) => console.error("Error:", error));
  counter++;
}

var userName = document.getElementById("name-username");
var userID = document.getElementById("name-userid");
userName.innerText = username;
userID.innerText = "@" + username;

var profileIcon = document.getElementById("profile-img");
profileIcon.innerText = username[0].toUpperCase();

var footerIcon = document.getElementById("footer-profile-img");
footerIcon.innerText = username[0].toUpperCase();

var postInput = document.getElementById("post-input");
postInput.addEventListener("input", handleInputChange);

function handleInputChange() {
  var postButton = document.getElementById("btn");
  var postText = postInput.value.trim();

  postButton.style.backgroundColor = postText !== "" ? "#1d9bf0" : "#0f4e78";
  postButton.style.color = postText !== "" ? "#e7e9ea" : "#808080";
}

// JavaScript function to handle posting
async function post() {
  var postButton = document.getElementById("btn");
  postButton.style.backgroundColor = "#0f4e78";
  postButton.style.color = "#808080";
  // Getting the input value
  var postText = document.getElementById("post-input").value;

  const postData = {
    username: username,
    content: postText,
  };

  try {
    const response = await fetch("http://localhost:3002/api/posts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postData),
    });
    console.log(postData);
    if (!response.ok) {
      throw new Error("Failed to add post");
    } else {
      addPost(postText, "new", username);
      console.log("post added");
    }
  } catch (error) {
    windows.alert("Error in adding post");
    console.error("Error:", error);
  }
}
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
    getActiveUsers();
    
    document.getElementById("home-text").style.fontWeight = "400";
    document.getElementById("message-text").style.fontWeight = "600";
    msgContainer.textContent = "Messages";
    msgContainer.style.display = "block";
    tweetBoxContainer.style.display = "none";
    postsContainer.style.display = "none";

  });


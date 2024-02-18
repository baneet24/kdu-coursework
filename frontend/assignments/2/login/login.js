
document
  .getElementById("login-btn")
  .addEventListener("click", async function (event) {
    const username = document.getElementById("login-name").value;
    const password = document.getElementById("login-password").value;

    const userData = {
      user_name: username,
      password: password,
    };

    try {
      const response = await fetch("http://localhost:3001/api/user/login/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (!response.ok) {
        throw new Error("Failed to submit data");
      } else {
        window.location.href = `/public/index.html?username=${username}`;
      
      }
    } catch (error) {
      console.error("Error:", error);
      window.alert("Please fill right credentials..");
      username.value = "";
      password.value = "";
    }
  });


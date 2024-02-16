document
  .getElementById("buy-btn")
  .addEventListener("click", async function (event) {
    const name = document.getElementById("price-value").value;
    const input = document.getElementById("input-quantity").value;

    const postData = {
      price: price,
    
    };
    try {
      const response = await fetch("http://localhost:3000/api/stocks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(postData),
      });
      if (!response.ok) {
        throw new Error("Failed to submit data");
      }
      console.log("Data submitted successfully");
      document.getElementById("price-value").value = "";
    } catch (error) {
      console.error("Error:", error);
    }
  });

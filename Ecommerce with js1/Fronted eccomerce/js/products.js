console.log("✅ products.js loaded");

const API_BASE_URL = "http://localhost:8080/api";
const TOKEN_KEY = "token";

function addToCart(productId) {
  const token = localStorage.getItem(TOKEN_KEY);

  if (!token) {
    alert("Please login first");
    window.location.href = "login.html";
    return;
  }

  fetch(`${API_BASE_URL}/cart/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify({
      productId: productId,
      quantity: 1
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Add to cart failed");
    return res.json();
  })
  .then(() => {
    alert("✅ Product added to cart");
  })
  .catch(err => {
    console.error(err);
    alert("Failed to add to cart");
  });
}
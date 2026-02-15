console.log("✅ cart.js loaded");

const API_BASE_URL = "http://localhost:8080/api";
const TOKEN_KEY = "token";

document.addEventListener("DOMContentLoaded", loadCart);

function loadCart() {
  const token = localStorage.getItem(TOKEN_KEY);

  if (!token) {
    alert("Please login");
    window.location.href = "login.html";
    return;
  }

  fetch(`${API_BASE_URL}/cart`, {
    headers: {
      "Authorization": "Bearer " + token
    }
  })
  .then(res => {
    if (!res.ok) throw new Error("Failed to load cart");
    return res.json();
  })
  .then(cart => {
    const cartList = document.getElementById("cart-list");
    const totalPrice = document.getElementById("total-price");

    cartList.innerHTML = "";

    if (!cart.items || cart.items.length === 0) {
      cartList.innerHTML = "<p>Your cart is empty</p>";
      totalPrice.innerText = "0";
      return;
    }

    cart.items.forEach(item => {
      cartList.innerHTML += `
        <div class="product-card">
          <h3>${item.productName}</h3>
          <p>Price: ₹${item.price}</p>
          <p>Quantity: ${item.quantity}</p>
          <p>Subtotal: ₹${item.price * item.quantity}</p>
        </div>
      `;
    });

    totalPrice.innerText = cart.totalAmount;
  })
  .catch(err => {
    console.error(err);
    alert("Failed to load cart");
  });
}
function placeOrder() {
  const token = localStorage.getItem("token");
  const paymentMethod = document.getElementById("payment-method").value;

  if (!paymentMethod) {
    alert("Please select a payment method");
    return;
  }

  fetch("http://localhost:8080/api/orders/place", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify({
      paymentMethod: paymentMethod
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Order failed");
    return res.json();
  })
  .then(() => {
    alert("✅ Order placed successfully");
    window.location.href = "orders.html";
  })
  .catch(err => {
    console.error(err);
    alert("Failed to place order");
  });
}
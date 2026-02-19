// ================================
// CUSTOMER.JS
// ================================

console.log("✅ customer.js loaded");

// 🔹 Load products when page loads
window.onload = loadProducts;

function loadProducts() {
  const productList = document.getElementById("product-list");

  if (!productList) {
    console.error("❌ product-list not found in HTML");
    return;
  }

  fetch(`${API_BASE_URL}/products`)
    .then(res => {
      if (!res.ok) {
        throw new Error("Failed to fetch products");
      }
      return res.json();
    })
    .then(products => {
      console.log("📦 Products:", products);

      productList.innerHTML = "";

      if (!products || products.length === 0) {
        productList.innerHTML = "<p>No products available</p>";
        return;
      }

      products.forEach(p => {
        const stock = p.inventory
          ? p.inventory.quantityAvailable
          : 0;

        productList.innerHTML += `
          <div class="product-card">
            <h3>${p.productName}</h3>
            <p><strong>Price:</strong> ₹${p.price}</p>
            <p><strong>Stock:</strong> ${stock}</p>

            <label for="qty-${p.productId}">Quantity:</label>
            <input
              type="number"
              id="qty-${p.productId}"
              min="1"
              max="${stock}"
              value="1"
            />

            <button onclick="addToCart(${p.productId})"
              ${stock === 0 ? "disabled" : ""}>
              Add to Cart
            </button>
          </div>
        `;
      });
    })
    .catch(err => {
      console.error("❌ Error loading products:", err);
      productList.innerHTML =
        "<p>❌ Failed to load products</p>";
    });
}

// 🔹 Add product to cart
function addToCart(productId) {
  const token = localStorage.getItem("token");

  if (!token) {
    alert("Please login first");
    window.location.href = "../login.html";
    return;
  }

  const qtyInput =
    document.getElementById(`qty-${productId}`);
  const quantity = Number(qtyInput.value);

  if (!quantity || quantity <= 0) {
    alert("Invalid quantity");
    return;
  }

  fetch(`${API_BASE_URL}/customer/cart/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify({
      productId: productId,
      quantity: quantity
    })
  })
    .then(res => {
      if (!res.ok) {
        throw new Error("Add to cart failed");
      }
      return res.json();
    })
    .then(() => {
      alert("✅ Product added to cart");
    })
    .catch(err => {
      console.error("❌ Add to cart error:", err);
      alert("❌ Failed to add product to cart");
    });
}
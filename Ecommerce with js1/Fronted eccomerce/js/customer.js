console.log("✅ customer.js loaded");

// 🔹 LOAD PRODUCTS
function loadProducts() {
  const productList = document.getElementById("product-list");

  if (!productList) {
    console.error("❌ product-list not found");
    return;
  }

  fetch(`${API_BASE_URL}/products`)
    .then(res => {
      if (!res.ok) throw new Error("Failed to fetch products");
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
        productList.innerHTML += `
          <div class="product-card">
            <img src="${p.imageUrl || 'https://via.placeholder.com/200'}" />
            <h3>${p.productName}</h3>
            <p>₹${p.price}</p>
            <button onclick="addToCart(${p.productId})">
              Add to Cart
            </button>
          </div>
        `;
      });
    })
    .catch(err => {
      console.error(err);
      productList.innerHTML = "<p>Failed to load products</p>";
    });
}

// 🔹 ADD TO CART
function addToCart(productId) {
  console.log("🛒 Add to cart clicked:", productId);

  const token = localStorage.getItem("token");

  if (!token) {
    alert("Please login first");
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
    if (!res.ok) throw new Error("Add failed");
    return res.json();
  })
  .then(() => {
    alert("✅ Product added to cart");
  })
  .catch(err => {
    console.error(err);
    alert("❌ Failed to add to cart");
  });
}
console.log("✅ customer.js loaded");

document.addEventListener("DOMContentLoaded", loadProducts);

async function loadProducts() {

    const productList = document.getElementById("product-list");

    if (!productList) return;

    try {

        const response = await fetch(`${API_BASE_URL}/products`);

        if (!response.ok) {
            throw new Error("Failed to fetch products");
        }

        const products = await response.json();
        productList.innerHTML = "";

        if (!products || products.length === 0) {
            productList.innerHTML = "<p>No products available</p>";
            return;
        }

        products.forEach(p => {

            const stock = p.inventory ? p.inventory.quantityAvailable : 0;

            productList.innerHTML += `
                <div class="product-card">
                    <h3>${p.productName}</h3>
                    <p><strong>Price:</strong> ₹${p.price}</p>
                    <p><strong>Stock:</strong> ${stock}</p>

                    <input type="number"
                        id="qty-${p.productId}"
                        min="1"
                        max="${stock}"
                        value="1" />

                    <button onclick="addToCart(${p.productId})"
                        ${stock === 0 ? "disabled" : ""}>
                        Add to Cart
                    </button>
                </div>
            `;
        });

    } catch (err) {
        console.error("❌ Error loading products:", err);
        productList.innerHTML = "<p>❌ Failed to load products</p>";
    }
}

async function addToCart(productId) {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Please login first");
        window.location.href = "../login.html";
        return;
    }

    const quantity = Number(document.getElementById(`qty-${productId}`).value);

    if (!quantity || quantity <= 0) {
        alert("Invalid quantity");
        return;
    }

    try {

        const response = await fetch(`${API_BASE_URL}/customer/cart/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify({
                productId,
                quantity
            })
        });

        if (!response.ok) {
            throw new Error("Add to cart failed");
        }

        alert("✅ Product added to cart");

    } catch (error) {
        console.error("❌ Add to cart error:", error);
        alert("❌ Failed to add product to cart");
    }
}
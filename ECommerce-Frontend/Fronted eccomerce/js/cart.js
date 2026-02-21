console.log("✅ cart.js loaded");

const formatRupee = (num) => {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        maximumFractionDigits: 0
    }).format(num || 0);
};

document.addEventListener("DOMContentLoaded", loadCart);

// ================= LOAD CART =================

async function loadCart() {

    const token = localStorage.getItem("token");
    if (!token) return;

    try {

        const response = await fetch(`${API_BASE_URL}/customer/cart`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load cart");
        }

        const data = await response.json();

        const cartList = document.getElementById("cart-list");
        const subtotalEl = document.getElementById("subtotal-val");
        const totalEl = document.getElementById("total-val");
        const badgeEl = document.getElementById("item-count-badge");

        cartList.innerHTML = "";

        if (!data.items || data.items.length === 0) {

            cartList.innerHTML = "<h3>Your cart is empty</h3>";
            subtotalEl.innerText = formatRupee(0);
            totalEl.innerText = formatRupee(0);
            badgeEl.innerText = "0 Items";
            return;
        }

        let totalAmount = 0;

        data.items.forEach(item => {

            const itemTotal = item.price * item.quantity;
            totalAmount += itemTotal;

            cartList.innerHTML += `
                <div class="cart-item">
                    <div>
                        <h4>${item.productName}</h4>
                        <p>Quantity: ${item.quantity}</p>
                    </div>
                    <div>
                        <span>${formatRupee(itemTotal)}</span>
                    </div>
                </div>
            `;
        });

        badgeEl.innerText = data.items.length + " Items";
        subtotalEl.innerText = formatRupee(totalAmount);
        totalEl.innerText = formatRupee(totalAmount);

    } catch (error) {
        console.error("Cart load error:", error);
    }
}

/// ================= CLEAR CART =================

document.addEventListener("DOMContentLoaded", () => {
    const clearBtn = document.getElementById("clearCartBtn");
    if (clearBtn) {
        clearBtn.addEventListener("click", clearCart);
    }
});

async function clearCart() {

    console.log("Clear cart button clicked"); // for testing

    const token = localStorage.getItem("token");
    if (!token) return;

    const confirmClear = confirm("Are you sure you want to clear your cart?");
    if (!confirmClear) return;

    try {

        const response = await fetch(`${API_BASE_URL}/customer/cart/clear`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

      if (!response.ok) {
    
    document.getElementById("cart-list").innerHTML = "<h3>Your cart is empty</h3>";
    document.getElementById("subtotal-val").innerText = formatRupee(0);
    document.getElementById("total-val").innerText = formatRupee(0);
    document.getElementById("item-count-badge").innerText = "0 Items";
    return;
}

        window.location.reload();

    } catch (error) {
        console.error("Clear cart error:", error);
        alert("❌ Failed to clear cart");
    }
}

async function placeOrder() {

    const token = localStorage.getItem("token");
    if (!token) return;

    const paymentMethod = document.getElementById("payment-method").value;

    if (!paymentMethod) {
        alert("Please select payment method");
        return;
    }

    try {

        const response = await fetch(`${API_BASE_URL}/customer/orders/place`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify({
                paymentMethod: paymentMethod
            })
        });

        if (!response.ok) {
            throw new Error("Order failed");
        }

        alert("✅ Order placed successfully");
        window.location.href = "orders.html";

    } catch (error) {
        console.error("Order error:", error);
        alert("❌ Order failed");
    }
}
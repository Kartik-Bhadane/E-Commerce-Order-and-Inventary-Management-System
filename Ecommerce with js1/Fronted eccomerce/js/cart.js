console.log("API_BASE_URL =", API_BASE_URL);
const formatRupee = (num) => {
    return new Intl.NumberFormat('en-IN', {
        style: 'currency',
        currency: 'INR',
        maximumFractionDigits: 0
    }).format(num);
};

const token = localStorage.getItem("token");

async function loadCart() {

    const response = await fetch(`${API_BASE_URL}/customer/cart`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    const data = await response.json();

    const cartList = document.getElementById('cart-list');
    const subtotalEl = document.getElementById('subtotal-val');
    const totalEl = document.getElementById('total-val');
    const badgeEl = document.getElementById('item-count-badge');

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

        let itemTotal = item.price * item.quantity;
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
}

async function addToCart(productId, quantity = 1) {

    await fetch(`${API_BASE_URL}/customer/cart/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            productId: productId,
            quantity: quantity
        })
    });

    alert("Added to cart!");
    loadCart();
}

async function clearCart() {

    await fetch(`${API_BASE_URL}/customer/cart/clear`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    loadCart();
}
async function placeOrder() {

    const response = await fetch(`${API_BASE_URL}/customer/orders/place`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    if (response.ok) {
        window.location.href = "orders.html";
    }
}

document.addEventListener("DOMContentLoaded", loadCart);

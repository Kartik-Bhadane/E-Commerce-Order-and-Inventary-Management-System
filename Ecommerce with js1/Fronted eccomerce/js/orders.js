// ================================
// ADMIN: LOAD ALL ORDERS
// GET /api/orders
// ================================
async function loadAllOrders() {
    const response = await fetch(`${API_BASE_URL}/orders`, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    const orders = await response.json();

    const table = document.getElementById("ordersTable");
    if (!table) return;

    table.innerHTML = "";

    orders.forEach(o => {
        table.innerHTML += `
            <tr>
                <td>${o.id}</td>
                <td>₹${o.totalAmount}</td>
                <td>${o.status}</td>
                <td>
                    <button onclick="cancelOrder(${o.id})">
                        Cancel
                    </button>
                </td>
            </tr>
        `;
    });
}


// ================================
// CANCEL ORDER
// PUT /api/orders/cancel/{id}
// ================================
async function cancelOrder(orderId) {
    await fetch(`${API_BASE_URL}/orders/cancel/${orderId}`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    alert("Order cancelled");
    loadAllOrders();
}

document.addEventListener("DOMContentLoaded", loadAllOrders);
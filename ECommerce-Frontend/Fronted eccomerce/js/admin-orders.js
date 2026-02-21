console.log("✅ admin-orders.js loaded");

async function loadOrders() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/orders`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load orders");
        }

        const orders = await response.json();
        console.log("📦 Admin Orders:", orders);

        const table = document.getElementById("orderTable");
        table.innerHTML = "";

        orders.forEach(order => {

            let statusClass = "instock";
            if (order.status === "PENDING") statusClass = "lowstock";
            if (order.status === "CANCELLED") statusClass = "outstock";

            table.innerHTML += `
                <tr>
                    <td>#VM-${order.orderId}</td>
                    <td>${order.user?.email || "N/A"}</td>
                    <td>${new Date(order.orderDate).toLocaleString()}</td>
                    <td>₹${order.totalAmount}</td>
                    <td>
                        <span class="status ${statusClass}">
                            ${order.status}
                        </span>
                    </td>
                   <td>
    <button class="action-icon cancel" onclick="cancelOrder(${order.orderId})">
        <i class="fas fa-times-circle"></i>
    </button>
</td>

                </tr>
            `;
        });

    } catch (error) {
        console.error("❌ Admin order load error:", error);
        alert("Failed to load orders");
    }
}


async function cancelOrder(orderId) {

    if (!confirm("Cancel this order?")) return;

    await fetch(`${API_BASE_URL}/admin/orders/${orderId}/cancel`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    loadOrders();
}
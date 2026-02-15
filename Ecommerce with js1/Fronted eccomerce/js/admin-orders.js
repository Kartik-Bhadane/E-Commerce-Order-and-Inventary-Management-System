// ============================
// LOAD ORDERS
// ============================
async function loadOrders() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/orders`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const orders = await response.json();
        const table = document.getElementById("orderTable");
        table.innerHTML = "";

        orders.forEach(order => {

            let statusClass = "instock";

            if (order.status === "PENDING") statusClass = "lowstock";
            if (order.status === "CANCELLED") statusClass = "outstock";

            table.innerHTML += `
                <tr>
                    <td>#${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${new Date(order.date).toLocaleDateString()}</td>
                    <td>$${order.totalAmount}</td>
                    <td>
                        <span class="status ${statusClass}">
                            ${order.status}
                        </span>
                    </td>
                    <td>
                        <button onclick="updateStatus(${order.id})">Update</button>
                        <button onclick="cancelOrder(${order.id})">Cancel</button>
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Order load error:", error);
        alert("Failed to load orders.");
    }
}


// ============================
// UPDATE STATUS
// ============================
async function updateStatus(id) {

    const status = prompt("Enter status (PENDING, COMPLETED, CANCELLED):");

    if (!status) return;

    await fetch(`${API_BASE_URL}/admin/orders/${id}/status`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ status })
    });

    loadOrders();
}


// ============================
// CANCEL ORDER
// ============================
async function cancelOrder(id) {

    if (!confirm("Cancel this order?")) return;

    await fetch(`${API_BASE_URL}/admin/orders/${id}/cancel`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    loadOrders();
}


// ============================
// EXPORT ORDERS
// ============================
function exportOrders() {
    window.open(`${API_BASE_URL}/admin/orders/export`, "_blank");
}

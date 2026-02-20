console.log("✅ orders.js loaded");

const formatRupee = (num) => {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        maximumFractionDigits: 0
    }).format(num || 0);
};

document.addEventListener("DOMContentLoaded", loadMyOrders);

async function loadMyOrders() {

    const orderList = document.getElementById("order-list");
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Please login again");
        window.location.href = "../login.html";
        return;
    }

    try {

        const response = await fetch(`${API_BASE_URL}/customer/orders`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load orders");
        }

        const orders = await response.json();
        orderList.innerHTML = "";

        if (!orders || orders.length === 0) {
            orderList.innerHTML = `
                <div class="empty-orders">
                    <p>You haven't placed any orders yet.</p>
                    <a href="customerdash.html">Start Shopping</a>
                </div>
            `;
            return;
        }

        orders.reverse().forEach(order => {

            const itemsHtml = order.items.map(item => `
                <div class="order-item-row">
                    <span>${item.product.productName} (x${item.quantity})</span>
                    <span>${formatRupee(item.price * item.quantity)}</span>
                </div>
            `).join("");

            const orderCard = document.createElement("div");
            orderCard.className = "order-card";

            orderCard.innerHTML = `
                <div class="order-meta">
                    <div>
                        <span class="label">ORDER ID</span>
                        <span class="value">#VM-${order.orderId}</span>
                    </div>
                    <div>
                        <span class="label">DATE</span>
                        <span class="value">
                            ${new Date(order.orderDate).toLocaleString()}
                        </span>
                    </div>
                    <div>
                        <span class="status-badge ${order.status ? order.status.toLowerCase() : ""}">
                            ${order.status || "Placed"}
                        </span>
                    </div>
                </div>

                <div class="order-items">
                    ${itemsHtml}
                </div>

                <div class="order-footer">
                    <strong>Total Paid: ${formatRupee(order.totalAmount)}</strong>
                </div>
            `;

            orderList.appendChild(orderCard);
        });

    } catch (err) {
        console.error("❌ Error loading orders:", err);
        orderList.innerHTML = "<p>❌ Failed to load orders</p>";
    }
}
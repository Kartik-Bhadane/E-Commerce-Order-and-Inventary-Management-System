// Helper to format currency
const formatRupee = (num) => {
    return new Intl.NumberFormat('en-IN', {
        style: 'currency',
        currency: 'INR',
        maximumFractionDigits: 0
    }).format(num);
};

function loadOrders() {
    const ordersList = document.getElementById('orders-list');
    // Retrieve orders from localStorage
    const orders = JSON.parse(localStorage.getItem('orderHistory')) || [];

    if (orders.length === 0) {
        ordersList.innerHTML = `
            <div class="empty-orders">
                <p>You haven't placed any orders yet.</p>
                <a href="dashboard.html">Start Shopping</a>
            </div>`;
        return;
    }

    ordersList.innerHTML = '';

    // Display orders (Latest first)
    orders.reverse().forEach((order) => {
        const orderCard = document.createElement('div');
        orderCard.className = 'order-card';
        
        orderCard.innerHTML = `
            <div class="order-meta">
                <div>
                    <span class="label">ORDER ID</span>
                    <span class="value">#VM-${order.id}</span>
                </div>
                <div>
                    <span class="label">DATE</span>
                    <span class="value">${order.date}</span>
                </div>
                <div>
                    <span class="status-badge ${order.status.toLowerCase()}">${order.status}</span>
                </div>
            </div>
            <div class="order-items">
                ${order.items.map(item => `
                    <div class="order-item-row">
                        <span>${item.name} (x${item.qty})</span>
                        <span>${formatRupee(item.price * item.qty)}</span>
                    </div>
                `).join('')}
            </div>
            <div class="order-footer">
                <span>Total Paid: <strong>${formatRupee(order.total)}</strong></span>
                <span class="pay-method">Via ${order.paymentMethod}</span>
            </div>
        `;
        ordersList.appendChild(orderCard);
    });
}
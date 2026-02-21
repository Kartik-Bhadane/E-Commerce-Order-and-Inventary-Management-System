requireLogin();

async function loadDashboard() {
    try {

        const token = localStorage.getItem("token");

        // Fetch products
        const productsRes = await fetch(`${API_BASE_URL}/products`, {
            headers: { "Authorization": "Bearer " + token }
        });
        const products = await productsRes.json();

        // Fetch orders
        const ordersRes = await fetch(`${API_BASE_URL}/orders`, {
            headers: { "Authorization": "Bearer " + token }
        });
        const orders = await ordersRes.json();

        // Fetch users (customers)
        const usersRes = await fetch(`${API_BASE_URL}/users`, {
            headers: { "Authorization": "Bearer " + token }
        });
        const users = await usersRes.json();

        // Update Stats
        document.getElementById("totalProducts").innerText = products.length;
        document.getElementById("totalOrders").innerText = orders.length;
        document.getElementById("totalCustomers").innerText = users.length;

        // Calculate Revenue
        let revenue = 0;
        orders.forEach(order => {
            revenue += order.totalAmount || 0;
        });

        document.getElementById("totalRevenue").innerText = "₹" + revenue;

        // Recent Orders (last 5)
        const table = document.getElementById("recentOrdersTable");
        table.innerHTML = "";

        orders.slice(0, 5).forEach(order => {
            table.innerHTML += `
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customer?.name || "N/A"}</td>
                    <td>₹${order.totalAmount || 0}</td>
                    <td>${order.status || "Pending"}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Dashboard Load Error:", error);
    }
}function logout() {

    if (!confirm("Are you sure you want to logout?"))
        return;

    // Remove token
    localStorage.removeItem("token");


    localStorage.removeItem("role");
    localStorage.removeItem("email");

  
    window.location.href = "../login.html";
}


document.addEventListener("DOMContentLoaded", loadDashboard);

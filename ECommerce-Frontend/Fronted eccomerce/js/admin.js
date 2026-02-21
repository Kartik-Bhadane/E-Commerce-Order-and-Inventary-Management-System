
// LOAD DASHBOARD DATA

async function loadDashboardData() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/dashboard`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const data = await response.json();

        document.getElementById("totalRevenue").innerText = "$" + data.totalRevenue;
        document.getElementById("totalOrders").innerText = data.totalOrders;
        document.getElementById("totalCustomers").innerText = data.totalCustomers;
        document.getElementById("totalProducts").innerText = data.totalProducts;

        loadRecentOrders();

    } catch (error) {
        console.error("Dashboard error:", error);
        alert("Failed to load dashboard data.");
    }
}


// LOAD RECENT 

async function loadRecentOrders() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/orders/recent`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const orders = await response.json();
        const table = document.getElementById("recentOrders");

        table.innerHTML = "";

        orders.forEach(order => {
            table.innerHTML += `
                <tr>
                    <td>#${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>$${order.totalAmount}</td>
                    <td>${order.status}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Recent orders error:", error);
    }
}


function logout() {
    localStorage.clear();
    window.location.href = "../login.html";
}

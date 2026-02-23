requireLogin();

async function fetchData(url, headers) {
    const res = await fetch(url, { headers });

    if (!res.ok) {
        throw new Error("API Error: " + res.status);
    }

    return res.json();
}


async function loadDashboard() {
    try {
        const token = localStorage.getItem("token");

        const headers = {
            "Authorization": "Bearer " + token
        };

        // =========================
        // FETCH TOTAL PRODUCTS
        // =========================
        const productRes = await fetch(`${API_BASE_URL}/admin/orders/totalproducts`, { headers });
        const totalProducts = await productRes.json();
        document.getElementById("totalProducts").innerText = totalProducts;


        // =========================
        // FETCH TOTAL ORDERS
        // =========================
        const orderRes = await fetch(`${API_BASE_URL}/admin/orders/totalorders`, { headers });
        const totalOrders = await orderRes.json();
        document.getElementById("totalOrders").innerText = totalOrders;


    
        const userRes = await fetch(`${API_BASE_URL}/admin/orders/totalcustomer`, { headers });
        const totalCustomers = await userRes.json();
        document.getElementById("totalCustomers").innerText = totalCustomers;


    

        const totalrevenue = await fetchData(`${API_BASE_URL}/admin/orders/totalrevenue`, headers);
document.getElementById("totalRevenue").innerText = "₹" + totalrevenue;



        const orders = await fetchData(`${API_BASE_URL}/admin/orders/getorder`,headers);
        const table = document.getElementById("recentOrdersTable");
        table.innerHTML = "";

        orders.slice(-5).reverse().forEach(order => {
            table.innerHTML += `
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.userName || "CUstomer"}</td>
                    <td>₹${order.totalAmount || 0}</td>
                    <td>${order.status || "Pending"}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Dashboard Load Error:", error);
    }
}
function logout() {

    if (!confirm("Are you sure you want to logout?"))
        return;

    // Remove token
    localStorage.removeItem("token");


    localStorage.removeItem("role");
    localStorage.removeItem("email");

  
    window.location.href = "../login.html";
}

document.addEventListener("DOMContentLoaded", loadDashboard);


// requireLogin();

// async function loadDashboard() {
//     try {

//         const token = localStorage.getItem("token");

//         // Fetch products
//         const productsRes = await fetch(`${API_BASE_URL}/products`, {
//             headers: { "Authorization": "Bearer " + token }
//         });
//         const products = await productsRes.json();

//         // Fetch orders
//         const ordersRes = await fetch(`${API_BASE_URL}/orders`, {
//             headers: { "Authorization": "Bearer " + token }
//         });
//         const orders = await ordersRes.json();

//         // Fetch users (customers)
//         const usersRes = await fetch(`${API_BASE_URL}/users`, {
//             headers: { "Authorization": "Bearer " + token }
//         });
//         const users = await usersRes.json();

//         // Update Stats
//         document.getElementById("totalProducts").innerText = products.length;
//         document.getElementById("totalOrders").innerText = orders.length;
//         document.getElementById("totalCustomers").innerText = users.length;

//         // Calculate Revenue
//         let revenue = 0;
//         orders.forEach(order => {
//             revenue += order.totalAmount || 0;
//         });

//         document.getElementById("totalRevenue").innerText = "₹" + revenue;

//         // Recent Orders (last 5)
//         const table = document.getElementById("recentOrdersTable");
//         table.innerHTML = "";

//         orders.slice(0, 5).forEach(order => {
//             table.innerHTML += `
//                 <tr>
//                     <td>${order.id}</td>
//                     <td>${order.customer?.name || "N/A"}</td>
//                     <td>₹${order.totalAmount || 0}</td>
//                     <td>${order.status || "Pending"}</td>
//                 </tr>
//             `;
//         });

//     } catch (error) {
//         console.error("Dashboard Load Error:", error);
//     }
// }function logout() {

//     if (!confirm("Are you sure you want to logout?"))
//         return;

//     // Remove token
//     localStorage.removeItem("token");


//     localStorage.removeItem("role");
//     localStorage.removeItem("email");

  
//     window.location.href = "../login.html";
// }


// document.addEventListener("DOMContentLoaded", loadDashboard);

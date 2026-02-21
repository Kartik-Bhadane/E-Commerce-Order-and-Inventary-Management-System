console.log("✅ admin-cust.js loaded");

document.addEventListener("DOMContentLoaded", loadCustomers);

async function loadCustomers() {
    try {
        const token = localStorage.getItem("token");

        if (!token) {
            alert("No token found. Please login again.");
            return;
        }

        const response = await fetch(`${API_BASE_URL}/admin/customers`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load customers. Status: " + response.status);
        }

        const customers = await response.json();
        console.log("📦 Customers:", customers);

        const table = document.getElementById("customerTable");
        table.innerHTML = "";

        customers.forEach(c => {
            table.innerHTML += `
                <tr>
                    <td>${c.fullName || "N/A"}</td>
                    <td>${c.email}</td>
                    <td>${c.role}</td>
                </tr>
            `;
        });

    } catch (err) {
        console.error("❌ Error loading customers:", err);
        alert("Failed to load customers");
    }
}
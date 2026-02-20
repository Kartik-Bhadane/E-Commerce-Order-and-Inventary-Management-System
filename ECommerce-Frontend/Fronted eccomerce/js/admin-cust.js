console.log("✅ admin-cust.js loaded");

async function loadCustomers() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/customers`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load customers");
        }

        const customers = await response.json();
        console.log("📦 Customers:", customers);

        const table = document.getElementById("customerTable");
        table.innerHTML = "";

        customers.forEach(c => {
            table.innerHTML += `
                <tr>
                    <td>${c.name || "N/A"}</td>
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

document.addEventListener("DOMContentLoaded", loadCustomers);
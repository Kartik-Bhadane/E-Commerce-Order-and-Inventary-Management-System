requireLogin();

async function loadCustomers() {
    try {
        const response = await fetch(`${API_BASE_URL}/orders`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const orders = await response.json();
        const table = document.getElementById("customerTable");

        table.innerHTML = "";

        orders.forEach(order => {

            let productList = "";

            if (order.items && order.items.length > 0) {
                order.items.forEach(item => {
                    productList += `${item.product.name} (Qty: ${item.quantity})<br>`;
                });
            } else {
                productList = "No products";
            }

            table.innerHTML += `
                <tr>
                    <td>${order.customer?.name || "N/A"}</td>
                    <td>${order.customer?.email || "N/A"}</td>
                    <td>${productList}</td>
                </tr>
            `;
        });

    } catch (error) {
        console.error("Error loading customers:", error);
    }
}

document.addEventListener("DOMContentLoaded", loadCustomers);

async function loadInventory() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/products`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const products = await response.json();
        const table = document.getElementById("inventoryTable");

        table.innerHTML = "";

        let total = products.length;
        let inStock = 0;
        let lowStock = 0;
        let outStock = 0;

        products.forEach(product => {

            let statusClass = "";
            let statusText = "";

            if (product.quantity === 0) {
                statusClass = "outstock";
                statusText = "Out of Stock";
                outStock++;
            } 
            else if (product.quantity <= 10) {
                statusClass = "lowstock";
                statusText = "Low Stock";
                lowStock++;
            } 
            else {
                statusClass = "instock";
                statusText = "In Stock";
                inStock++;
            }

            table.innerHTML += `
                <tr>
                    <td>${product.name}</td>
                    <td><span class="status ${statusClass}">${statusText}</span></td>
                    <td>${product.quantity}</td>
                    <td>$${product.price}</td>
                    <td>${product.sales || 0}</td>
                   <td>
    <button class="btn-edit" onclick="editProduct(${product.id})">
        <i class="fas fa-pen"></i>
    </button>
    <button class="btn-delete" onclick="deleteProduct(${product.id})">
        <i class="fas fa-trash"></i>
    </button>
</td>

                </tr>
            `;
        });

        // Update summary cards
        document.getElementById("totalProducts").innerText = total;
        document.getElementById("inStock").innerText = inStock;
        document.getElementById("lowStock").innerText = lowStock;
        document.getElementById("outStock").innerText = outStock;

    } catch (error) {
        console.error("Inventory load error:", error);
        alert("Failed to load inventory.");
    }
}

async function addProduct() {
    const name = prompt("Product name:");
    const price = prompt("Price:");
    const quantity = prompt("Quantity:");

    if (!name || !price || !quantity) return;

    await fetch(`${API_BASE_URL}/admin/products`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({
            name,
            price: parseFloat(price),
            quantity: parseInt(quantity)
        })
    });

    loadInventory();
}



async function editProduct(id) {
    const quantity = prompt("Enter new quantity:");

    if (!quantity) return;

    await fetch(`${API_BASE_URL}/admin/products/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({
            quantity: parseInt(quantity)
        })
    });

    loadInventory();
}



async function deleteProduct(id) {
    if (!confirm("Delete this product?")) return;

    await fetch(`${API_BASE_URL}/admin/products/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    loadInventory();
}
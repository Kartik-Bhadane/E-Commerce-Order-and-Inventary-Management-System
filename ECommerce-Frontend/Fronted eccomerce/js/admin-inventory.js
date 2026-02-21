
async function loadInventory() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/products`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (!response.ok) {
            throw new Error("Failed to fetch products");
        }

        const products = await response.json();
        const table = document.getElementById("inventoryTable");

        table.innerHTML = "";

        let total = products.length;
        let inStock = 0;
        let lowStock = 0;
        let outStock = 0;
products.forEach(product => {

    const name = product.productName || "N/A";
    const quantity = Number(product.inventory?.quantityAvailable) || 0;
    const price = Number(product.price) || 0;

    let statusClass = "";
    let statusText = "";

    if (quantity === 0) {
        statusClass = "outstock";
        statusText = "Out of Stock";
        outStock++;
    } 
    else if (quantity <= 10) {
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
            <td>${name}</td>
            <td>
                <span class="status ${statusClass}">
                    ${statusText}
                </span>
            </td>
            <td>${quantity}</td>
            <td>₹${price.toLocaleString("en-IN")}</td>
            <td>0</td>
            <td>
                <button class="btn-edit" onclick="editProduct(${product.productId})">
                    <i class="fas fa-pen"></i>
                </button>
                <button class="btn-delete" onclick="deleteProduct(${product.productId})">
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

// ================= ADD PRODUCT =================
async function addProduct() {

    const name = prompt("Product name:");
    const price = prompt("Price (₹):");
    const quantity = prompt("Quantity:");

    if (!name || !price || !quantity) {
        alert("All fields are required.");
        return;
    }

    try {
        await fetch(`${API_BASE_URL}/admin/products`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify({
                name: name,
                price: parseFloat(price),
                quantity: parseInt(quantity)
            })
        });

        loadInventory();

    } catch (error) {
        console.error("Add product error:", error);
        alert("Failed to add product.");
    }
}

// ================= EDIT PRODUCT =================
async function editProduct(id) {

    const quantity = prompt("Enter new quantity:");

    if (!quantity) return;

    try {
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

    } catch (error) {
        console.error("Edit product error:", error);
        alert("Failed to update product.");
    }
}

// ================= DELETE PRODUCT =================
async function deleteProduct(id) {

    if (!confirm("Delete this product?")) return;

    try {const response = await fetch(`${API_BASE_URL}/api/admin/products/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (!response.ok) {
            const text = await response.text();
            throw new Error(text);
        }

        alert("Product deleted successfully!");
        loadInventory();

    } catch (error) {
        console.error("Delete product error:", error);
        alert("Delete failed: " + error.message);
    }
}
// ================= AUTO LOAD =================
document.addEventListener("DOMContentLoaded", loadInventory);
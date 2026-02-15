// ================================
// ADMIN: LOAD PRODUCTS
// GET /api/products
// ================================
async function loadAdminProducts() {
    const response = await fetch(`${API_BASE_URL}/products`, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    const products = await response.json();

    const table = document.getElementById("productsTable");
    if (!table) return;

    table.innerHTML = "";

    products.forEach(p => {
        table.innerHTML += `
            <tr>
                <td>${p.name}</td>
                <td>₹${p.price}</td>
                <td>${p.inventory?.stock ?? 0}</td>
                <td>
                    <button onclick="deleteProduct(${p.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}


// ================================
// ADMIN: ADD PRODUCT
// POST /api/products
// ================================
async function addProduct(product) {
    await fetch(`${API_BASE_URL}/products`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(product)
    });

    alert("Product added");
    loadAdminProducts();
}


// ================================
// ADMIN: DELETE PRODUCT
// DELETE /api/products/{id}
// ================================
async function deleteProduct(id) {
    await fetch(`${API_BASE_URL}/products/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    alert("Product deleted");
    loadAdminProducts();
}

document.addEventListener("DOMContentLoaded", loadAdminProducts);
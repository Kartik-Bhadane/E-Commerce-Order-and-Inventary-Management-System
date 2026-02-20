async function loadProducts() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/products`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (!response.ok) {
            alert("Failed to load products");
            return;
        }

        const products = await response.json();
        const table = document.getElementById("productsTable");
        table.innerHTML = "";

        products.forEach(p => {

            const quantity = p.inventory?.quantityAvailable ?? 0;

            table.innerHTML += `
                <tr>
                    <td>
                        <img src="${p.imageUrl || 'https://via.placeholder.com/60'}" 
                             style="width:60px;height:60px;border-radius:8px;object-fit:cover;">
                    </td>
                    <td>${p.productName}</td>
                    <td>₹${p.price}</td>
                    <td>${quantity}</td>
                    <td>
                       
                        <button class="btn-delete" onclick="deleteProduct(${p.productId})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        console.error(error);
    }
}


// ================= ADD PRODUCT =================



async function addProduct() {
    const name = document.getElementById("name").value.trim();
    const price = parseFloat(document.getElementById("price").value) || 0;
    const imageUrl = document.getElementById("imageUrl").value.trim() || "https://via.placeholder.com/60";
    const stock = parseInt(document.getElementById("stock").value) || 0;
    const categoryId = parseInt(document.getElementById("categoryId").value) || null;

    if (!name) {
        alert("Product Name is required");
        return;
    }

    const product = {
        productName: name,
        price: price,
        imageUrl: imageUrl,
        inventory: { quantityAvailable: stock }, // ✅ important
        categoryId: categoryId
    };

    try {
        const response = await fetch(`${API_BASE_URL}/admin/products`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify(product)
        });

        if (response.ok) {
            alert("Product added successfully");
            loadProducts();
            document.getElementById("name").value = "";
            document.getElementById("price").value = "";
            document.getElementById("imageUrl").value = "";
            document.getElementById("stock").value = "";
            document.getElementById("categoryId").value = "";
        } else {
            const err = await response.json();
            alert("Failed to add product: " + (err.message || response.statusText));
        }

    } catch (error) {
        console.error(error);
        alert("Error adding product");
    }
}



// ================= DELETE PRODUCT =================
async function deleteProduct(id) {
    if (!confirm("Are you sure?")) return;

    try {
        const response = await fetch(`${API_BASE_URL}/admin/products/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (response.ok) {
            alert("Deleted successfully");
            loadProducts();
        } else {
            alert("Delete failed");
        }

    } catch (error) {
        console.error(error);
    }
}

document.addEventListener("DOMContentLoaded", loadProducts);

// new 
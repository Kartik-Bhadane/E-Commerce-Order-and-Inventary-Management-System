async function loadProducts() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/products`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        if (response.status === 401) {
            alert("Session expired. Please login again.");
            localStorage.removeItem("token");
            window.location.href = "login.html";
            return;
        }

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
                         <button class="edit-btn" onclick="editProduct(${p.productId}, '${p.productName}', ${p.price}, '${p.imageUrl || ""}', '${p.description || ""}', ${p.inventory?.quantityAvailable ?? 0}, ${p.categoryId || 0})"> <i class="fas fa-trash"></i> 
                         Edit</button>
                        <button class="btn-delete" onclick="deleteProduct(${p.productId})">
                             <i class="fas fa-trash"></i>
                            delete
                        </button>
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        console.error(error);
    }
}


// ============= Update Product =================
function editProduct(id, name, price, imageUrl, description, stock, categoryId) {

    document.getElementById("name").value = name;
    document.getElementById("price").value = price;
    document.getElementById("imageUrl").value = imageUrl;
    document.getElementById("description").value = description;
    document.getElementById("stock").value = stock;
    document.getElementById("categoryId").value = categoryId;

    // store editing id
    window.editingProductId = id;

    // change button text
    document.querySelector(".primary-btn").innerText = "Update Product";
}

// ================= ADD PRODUCT =================





async function addProduct() {

    const name = document.getElementById("name").value.trim();
    const price = parseFloat(document.getElementById("price").value) || 0;
    const description = document.getElementById("description").value.trim();
    const imageUrl = document.getElementById("imageUrl").value.trim();
    const stock = parseInt(document.getElementById("stock").value) || 0;
    const categoryId = parseInt(document.getElementById("categoryId").value);

    if (!name) {
        alert("Product name required");
        return;
    }

    const product = {
        productName: name,
        price: price,
        description: description,
        imageUrl: imageUrl,
        categoryId: categoryId,
        initialStock: stock
    };

    try {

        // ================= UPDATE MODE =================
        if (window.editingProductId) {

            const response = await fetch(`${API_BASE_URL}/admin/products/${window.editingProductId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(product)
            });

            if (response.status === 401) {
                alert("Session expired. Please login again.");
                localStorage.removeItem("token");
                window.location.href = "login.html";
                return;
            }

            if (response.ok) {
                alert("Product updated successfully");
                window.editingProductId = null;
                document.querySelector(".primary-btn").innerText = "Add Product";
                clearForm();
                loadProducts();
            } else {
                alert("Update failed");
            }

        }

        // ================= ADD MODE =================
        else {

            const response = await fetch(`${API_BASE_URL}/admin/products`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(product)
            });

            if (response.status === 401) {
                alert("Session expired. Please login again.");
                localStorage.removeItem("token");
                window.location.href = "login.html";
                return;
            }

            if (response.ok) {
                alert("Product added successfully");
                clearForm();
                loadProducts();
            } else {
                alert("Add failed");
            }
        }

    } catch (error) {
        console.error(error);
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

function clearForm() {
    document.getElementById("name").value = "";
    document.getElementById("price").value = "";
    document.getElementById("stock").value = "";
    document.getElementById("description").value = "";
    document.getElementById("imageUrl").value = "";
     document.getElementById("categoryId").value = "";
}
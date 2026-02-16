function getAuthHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// =============================
// LOAD CATEGORIES
// =============================
async function loadCategories() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/categories`, {
            headers: getAuthHeaders()
        });

        if (response.status === 401) {
            alert("Session expired. Please login again.");
            window.location.href = "../login.html";
            return;
        }

        if (response.status === 403) {
            alert("Access denied. Admin only.");
            return;
        }

        if (!response.ok) {
            throw new Error("Failed to fetch categories");
        }

        const categories = await response.json();
        const table = document.getElementById("categoryTable");
        table.innerHTML = "";

        categories.forEach(category => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${category.categoryId}</td>
                <td>${category.categoryName}</td>
                <td>${category.description || "-"}</td>
                <td>
                    <button onclick="editCategory(${category.categoryId})">Edit</button>
                    <button onclick="deleteCategory(${category.categoryId})">Delete</button>
                </td>
            `;
            table.appendChild(row);
        });

    } catch (error) {
        console.error("Category load error:", error);
        alert("Failed to load categories.");
    }
}

// =============================
// ADD CATEGORY
// =============================
async function addCategory() {
    const categoryName = prompt("Enter category name:");
    if (!categoryName) return;

    const description = prompt("Enter category description:");
    if (!description) return;

    try {
        const response = await fetch(`${API_BASE_URL}/admin/categories/add`, {
            method: "POST",
            headers: getAuthHeaders(),
            body: JSON.stringify({ 
                categoryName,
                description
            })
        });

        if (response.status === 401) {
            alert("Session expired. Please login again.");
            window.location.href = "../login.html";
            return;
        }

        if (response.status === 403) {
            alert("Access denied. Admin only.");
            return;
        }

        if (!response.ok) {
            throw new Error("Failed to add category");
        }

        alert("Category added successfully!");
        loadCategories();

       


    } catch (error) {
        console.error("Add category error:", error);
        alert("Error adding category.");
    }
}

// =============================
// UPDATE CATEGORY
// =============================
async function editCategory(id) {

    if (!id) {
        alert("Invalid category ID");
        return;
    }

    const categoryName = prompt("Enter new category name:");
    if (!categoryName) return;

    const description = prompt("Enter new description:");
    if (!description) return;

    try {
        const response = await fetch(`${API_BASE_URL}/admin/categories/${id}`, {
            method: "PUT",
            headers: getAuthHeaders(),
            body: JSON.stringify({ 
                categoryName,
                description
            })
        });

        if (response.status === 401) {
            alert("Session expired. Please login again.");
            window.location.href = "../login.html";
            return;
        }

        if (response.status === 403) {
            alert("Access denied. Admin only.");
            return;
        }

        if (!response.ok) {
            throw new Error("Failed to update category");
        }

        alert("Category updated successfully!");
        loadCategories();

    } catch (error) {
        console.error("Update category error:", error);
        alert("Error updating category.");
    }
}

// =============================
// DELETE CATEGORY
// =============================
async function deleteCategory(id) {

    if (!id) {
        alert("Invalid category ID");
        return;
    }

    if (!confirm("Are you sure?")) return;

    try {
        const response = await fetch(`${API_BASE_URL}/admin/categories/${id}`, {
            method: "DELETE",
            headers: getAuthHeaders()
        });

        if (response.status === 401) {
            alert("Session expired. Please login again.");
            window.location.href = "../login.html";
            return;
        }

        if (response.status === 403) {
            alert("Access denied. Admin only.");
            return;
        }

        if (!response.ok) {
            throw new Error("Failed to delete category");
        }

        alert("Category deleted successfully!");
        loadCategories();

    } catch (error) {
        console.error("Delete category error:", error);
        alert("Error deleting category.");
    }
}


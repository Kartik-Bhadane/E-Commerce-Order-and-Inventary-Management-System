// =============================
// LOAD CATEGORIES
// =============================
async function loadCategories() {
    try {
        const response = await fetch(`${API_BASE_URL}/admin/categories`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        const categories = await response.json();
        const table = document.getElementById("categoryTable");
        table.innerHTML = "";

        categories.forEach(category => {
            table.innerHTML += `
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.totalProducts}</td>
                    <td>
                        <span class="status ${category.active ? 'instock' : 'lowstock'}">
                            ${category.active ? 'Active' : 'Inactive'}
                        </span>
                    </td>
                    <td>
                        <button onclick="editCategory(${category.id})">Edit</button>
                        <button onclick="deleteCategory(${category.id})">Delete</button>
                    </td>
                </tr>
            `;
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
    const name = prompt("Enter category name:");

    if (!name) return;

    try {
        await fetch(`${API_BASE_URL}/admin/categories`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify({ name })
        });

        loadCategories();

    } catch (error) {
        console.error("Add category error:", error);
    }
}


// =============================
// EDIT CATEGORY
// =============================
async function editCategory(id) {
    const newName = prompt("Enter new category name:");

    if (!newName) return;

    try {
        await fetch(`${API_BASE_URL}/admin/categories/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify({ name: newName })
        });

        loadCategories();

    } catch (error) {
        console.error("Edit error:", error);
    }
}


// =============================
// DELETE CATEGORY
// =============================
async function deleteCategory(id) {
    if (!confirm("Are you sure?")) return;

    try {
        await fetch(`${API_BASE_URL}/admin/categories/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        });

        loadCategories();

    } catch (error) {
        console.error("Delete error:", error);
    }
}

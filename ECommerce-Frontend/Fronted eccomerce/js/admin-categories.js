function getAuthHeaders() {
  return {
    "Content-Type": "application/json",
    Authorization: "Bearer " + localStorage.getItem("token"),
  };
}


async function loadCategories() {
  try {
    const response = await fetch(`${API_BASE_URL}/admin/categories`, {
      headers: getAuthHeaders(),
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

    categories.forEach((category) => {
      const row = document.createElement("tr");

      row.innerHTML = `
                <td>${category.categoryId}</td>
                <td>${category.categoryName}</td>
                <td>${category.description || "-"}</td>
      <td>
    <button class="btn-edit" onclick="editCategory(${category.categoryId})">
        <i class="fas fa-pen"></i>
    </button>
    <button class="btn-delete" onclick="deleteCategory(${category.categoryId})">
        <i class="fas fa-trash"></i>
    </button>
</td>

            `;

      table.appendChild(row);
    });
  } catch (error) {
    console.error("Category load error:", error);
    alert("Failed to load categories.");
  }
}

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
        description,
      }),
    });

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

async function editCategory(id) {
  if (!id) return alert("Invalid category ID");

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
        description,
      }),
    });

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


async function deleteCategory(id) {
  if (!id) return alert("Invalid category ID");
  if (!confirm("Are you sure you want to delete this category?")) return;

  try {
    const response = await fetch(`${API_BASE_URL}/admin/categories/${id}`, {
      method: "DELETE",
      headers: getAuthHeaders(),
    });

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


document.addEventListener("DOMContentLoaded", loadCategories);

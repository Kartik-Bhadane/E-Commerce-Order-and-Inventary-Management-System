const productForm = document.getElementById('product-form');

productForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const product = {
        name: document.getElementById('p-name').value,
        price: parseInt(document.getElementById('p-price').value),
        image: document.getElementById('p-img').value,
        description: document.getElementById('p-desc')?.value || ""
    };

    try {
        const response = await fetch(`${API_BASE_URL}/products`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify(product)
        });

        if (!response.ok) {
            alert("Failed to add product");
            return;
        }

        alert("Product Added Successfully!");

        // Stay on admin page
        window.location.href = "dashboard.html";

    } catch (err) {
        console.error(err);
        alert("Error adding product");
    }
});
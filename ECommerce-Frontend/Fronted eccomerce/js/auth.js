


// ================================
// LOGIN
// ================================
document.getElementById("loginForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    try {
        const data = await apiRequest("/auth/login", "POST", {
            email,
            password
        });

        if (data && data.token) {
            // Save JWT + role
            localStorage.setItem(STORAGE_KEYS.TOKEN, data.token);
            localStorage.setItem(STORAGE_KEYS.ROLE, data.role);

            alert("Login successful");

            // ✅ RELATIVE PATHS (IMPORTANT)
            if (data.role === "ADMIN") {
                window.location.href = "admin/dashboard.html";
            } else {
               window.location.href = "customer/customerdash.html";
            }
        } else {
            alert("Invalid credentials");
        }
    } catch (err) {
        console.error("Login error:", err);
        alert("Login failed");
    }
});


// ================================
// REGISTER
// ================================
document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();

    const user = {
        fullName: document.getElementById("name").value.trim(),
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value.trim(),
        role: "CUSTOMER"
    };

    try {
        const response = await fetch(`${API_BASE_URL}/auth/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert("Registration successful! Please login.");

            // ✅ RELATIVE PATH (CRITICAL FIX)
            window.location.href = "login.html";
        } else {
            alert("Registration failed");
        }
    } catch (err) {
        console.error("Register error:", err);
        alert("Server error during registration");
    }
});

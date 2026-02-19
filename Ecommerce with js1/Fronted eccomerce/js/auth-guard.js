function requireLogin() {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "../login.html";
    }
}

function requireAdmin() {
    const role = localStorage.getItem("role");
    if (role !== "ADMIN") {
        alert("Access denied");
        window.location.href = "../login.html";
    }
}
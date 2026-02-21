
const TokenService = {

    set: (token) => {
        localStorage.setItem("token", token);
    },

    get: () => {
        return localStorage.getItem("token");
    },

    remove: () => {
        localStorage.removeItem("token");
    }
};



const UserService = {

    set: (user) => {
        localStorage.setItem("user", JSON.stringify(user));
    },

    get: () => {
        return JSON.parse(localStorage.getItem("user"));
    },

    remove: () => {
        localStorage.removeItem("user");
    }
};



function formatPrice(price) {
    return "₹" + Number(price).toLocaleString("en-IN");
}


function showMessage(message, type = "success") {

    const alertBox = document.createElement("div");
    alertBox.innerText = message;
    alertBox.style.position = "fixed";
    alertBox.style.top = "20px";
    alertBox.style.right = "20px";
    alertBox.style.padding = "12px 20px";
    alertBox.style.borderRadius = "6px";
    alertBox.style.color = "white";
    alertBox.style.zIndex = "9999";

    if (type === "success") {
        alertBox.style.background = "#28a745";
    } else if (type === "error") {
        alertBox.style.background = "#dc3545";
    } else {
        alertBox.style.background = "#1f3c88";
    }

    document.body.appendChild(alertBox);

    setTimeout(() => {
        alertBox.remove();
    }, 3000);
}



function redirect(page) {
    window.location.href = page;
}

function updateCartCount() {
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    const cartCountElement = document.getElementById("cartCount");

    if (cartCountElement) {
        cartCountElement.innerText = cart.length;
    }
}


// ================================
// LOGOUT
// ================================

function logout() {
    TokenService.remove();
    UserService.remove();
    localStorage.removeItem("cart");
    redirect("login.html");
}

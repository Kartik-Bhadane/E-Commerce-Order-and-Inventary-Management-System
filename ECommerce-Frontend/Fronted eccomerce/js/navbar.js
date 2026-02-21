document.addEventListener("DOMContentLoaded", () => {

    updateCartCount();
    updateNavbar();
    setActiveLink();

});




function updateNavbar() {

    const user = UserService.get();
    const loginLink = document.getElementById("loginLink");
    const logoutLink = document.getElementById("logoutLink");
    const userName = document.getElementById("userName");
    const adminLink = document.getElementById("adminLink");

    if (user) {
        if (loginLink) loginLink.style.display = "none";
        if (logoutLink) logoutLink.style.display = "inline-block";
        if (userName) userName.innerText = "Hi, " + user.name;

        // Show admin panel if role is ADMIN
        if (user.role === "ADMIN" && adminLink) {
            adminLink.style.display = "inline-block";
        }

    } else {
        if (loginLink) loginLink.style.display = "inline-block";
        if (logoutLink) logoutLink.style.display = "none";
        if (adminLink) adminLink.style.display = "none";
    }
}


// ================================
// SET ACTIVE NAV LINK
// ================================

function setActiveLink() {

    const links = document.querySelectorAll(".nav-link");
    const currentPage = window.location.pathname.split("/").pop();

    links.forEach(link => {
        const href = link.getAttribute("href");

        if (href === currentPage) {
            link.classList.add("active");
        }
    });
}

// ================================
// COMMON API REQUEST
// ================================
async function apiRequest(endpoint, method = "GET", body = null, auth = false) {

    const headers = {
        "Content-Type": "application/json"
    };

    if (auth) {
        const token = localStorage.getItem(STORAGE_KEYS.TOKEN);
        if (token) {
            headers["Authorization"] = "Bearer " + token;
        }
    }

    const options = {
        method,
        headers
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    const response = await fetch(API_BASE_URL + endpoint, options);

    if (response.status === 401) {
        alert("Session expired. Please login again.");
        localStorage.clear();
        window.location.href = "/login.html";
        return;
    }

    return response.json();
}
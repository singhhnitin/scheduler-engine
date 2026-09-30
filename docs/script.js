const API_URL = "https://scheduler-engine.onrender.com";
const BASE_URL = `${API_URL}/schedule`;

// JWT is kept in memory for this page session
let token = null;

function authHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

function requireLogin() {
    if (!token) {
        alert("Please log in first");
        return false;
    }
    return true;
}

function register() {
    fetch(`${API_URL}/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: username.value, password: password.value })
    })
        .then(res => res.json())
        .then(data => alert(data.message || data.error));
}

function login() {
    fetch(`${API_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: username.value, password: password.value })
    })
        .then(res => res.json())
        .then(data => {
            if (data.token) {
                token = data.token;
                authStatus.textContent = `Logged in as ${username.value}`;
            } else {
                alert(data.error || "Login failed");
            }
        });
}

function addResource() {
    if (!requireLogin()) return;
    fetch(`${BASE_URL}/resource`, {
        method: "POST",
        headers: authHeaders(),
        body: JSON.stringify({
            id: resId.value,
            availableFrom: Number(resFrom.value),
            availableTo: Number(resTo.value)
        })
    }).then(res => alert(res.ok ? "Resource added" : `Error ${res.status}`));
}

function addAppointment() {
    if (!requireLogin()) return;
    fetch(`${BASE_URL}/appointment`, {
        method: "POST",
        headers: authHeaders(),
        body: JSON.stringify({
            id: appId.value,
            startTime: Number(appStart.value),
            endTime: Number(appEnd.value),
            duration: Number(appDuration.value),
            priority: Number(appPriority.value)
        })
    }).then(res => alert(res.ok ? "Appointment added" : `Error ${res.status}`));
}

function generateSchedule() {
    if (!requireLogin()) return;
    fetch(BASE_URL, { headers: authHeaders() })
        .then(res => res.json())
        .then(data => {
            output.textContent = JSON.stringify(data, null, 2);
        });
}

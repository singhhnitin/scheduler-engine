const BASE_URL = "https://scheduler-engine.onrender.com";

const output = document.getElementById("output");

// ---------- ADD RESOURCE ----------
function addResource() {
    const id = document.getElementById("resId").value;
    const from = Number(document.getElementById("resFrom").value);
    const to = Number(document.getElementById("resTo").value);

    fetch(`${BASE_URL}/schedule/resource`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            availableFrom: from,
            availableTo: to
        })
    })
    .then(res => res.text())
    .then(msg => {
        output.textContent = msg;
    })
    .catch(err => {
        output.textContent = "Error: " + err.message;
    });
}

// ---------- ADD APPOINTMENT ----------
function addAppointment() {
    const id = document.getElementById("appId").value;
    const start = Number(document.getElementById("appStart").value);
    const end = Number(document.getElementById("appEnd").value);
    const duration = Number(document.getElementById("appDuration").value);
    const priority = Number(document.getElementById("appPriority").value);

    fetch(`${BASE_URL}/schedule/appointment`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            startTime: start,
            endTime: end,
            duration: duration,
            priority: priority
        })
    })
    .then(res => res.text())
    .then(msg => {
        output.textContent = msg;
    })
    .catch(err => {
        output.textContent = "Error: " + err.message;
    });
}

// ---------- GENERATE SCHEDULE ----------
document.getElementById("generateBtn").addEventListener("click", () => {
    output.textContent = "Calling backend...";

    fetch(`${BASE_URL}/schedule`)
        .then(res => res.json())
        .then(data => {
            output.textContent = JSON.stringify(data, null, 2);
        })
        .catch(err => {
            output.textContent = "Error: " + err.message;
        });
});

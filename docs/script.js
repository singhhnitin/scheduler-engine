const BASE_URL = "https://scheduler-engine.onrender.com/schedule";

function addResource() {
    fetch(`${BASE_URL}/resource`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            id: resId.value,
            availableFrom: Number(resFrom.value),
            availableTo: Number(resTo.value)
        })
    }).then(() => alert("Resource added"));
}

function addAppointment() {
    fetch(`${BASE_URL}/appointment`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            id: appId.value,
            startTime: Number(appStart.value),
            endTime: Number(appEnd.value),
            duration: Number(appDuration.value),
            priority: Number(appPriority.value)
        })
    }).then(() => alert("Appointment added"));
}

function generateSchedule() {
    fetch(BASE_URL)
        .then(res => res.json())
        .then(data => {
            output.textContent = JSON.stringify(data, null, 2);
        });
}

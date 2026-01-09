const API_BASE_URL = "https://scheduler-engine.onrender.com";
const appointments = [];
const resources = [];

document.getElementById("generateBtn").addEventListener("click", generateSchedule);
async function generateSchedule() {
    const output = document.getElementById("output");
    output.textContent = "Calling backend...";

    try {
        const response = await fetch(`${API_BASE_URL}/schedule`);

        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }

        const data = await response.json();
        output.textContent = JSON.stringify(data, null, 2);
    } catch (error) {
        output.textContent = "Error: " + error.message;
    }
}
function addResource() {
    const resource = {
        id: document.getElementById("resId").value,
        availableFrom: Number(document.getElementById("resFrom").value),
        availableTo: Number(document.getElementById("resTo").value)
    };

    resources.push(resource);
    document.getElementById("output").textContent =
        "Resource added:\n" + JSON.stringify(resource, null, 2);
}
function addAppointment() {
    const appointment = {
        id: document.getElementById("appId").value,
        startTime: Number(document.getElementById("appStart").value),
        endTime: Number(document.getElementById("appEnd").value),
        duration: Number(document.getElementById("appDuration").value),
        priority: Number(document.getElementById("appPriority").value)
    };

    appointments.push(appointment);
    document.getElementById("output").textContent =
        "Appointment added:\n" + JSON.stringify(appointment, null, 2);
}

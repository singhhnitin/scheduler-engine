package service;

import model.Appointment;
import engine.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class SchedulingService {

    private List<Appointment> appointments;
    private List<Resource> resources;

    public SchedulingService() {
        appointments = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void cancelAppointment(String appointmentId) {
        appointments.removeIf(a -> a.getId().equals(appointmentId));
    }

    public Scheduler.ScheduleResult generateSchedule() {

        // STEP 1: consider only PENDING appointments
        List<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment a : appointments) {
            if ("PENDING".equals(a.getStatus())) {
                pendingAppointments.add(a);
            }
        }

        // STEP 2: reset resources
        resetResources();

        // STEP 3: run scheduler
        Scheduler.ScheduleResult result =
                Scheduler.scheduleAppointments(pendingAppointments, resources);

        // STEP 4: update appointment statuses
        for (Appointment a : appointments) {
            if (result.assigned.containsKey(a.getId())) {
                a.setStatus("ASSIGNED");
            } else if (result.rejected.contains(a.getId())) {
                a.setStatus("REJECTED");
            }
        }

        return result;
    }

    private void resetResources() {
        for (Resource r : resources) {
            r.setNextAvailableTime(r.getAvailableFrom());
        }
    }
}

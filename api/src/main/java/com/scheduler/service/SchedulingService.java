package com.scheduler.service;

import com.scheduler.model.Appointment;
import com.scheduler.engine.Scheduler;

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
        resetResources();
        return Scheduler.scheduleAppointments(appointments, resources);
    }

    private void resetResources() {
        for (Resource r : resources) {
            r.setNextAvailableTime(r.getAvailableFrom());
        }
    }
}

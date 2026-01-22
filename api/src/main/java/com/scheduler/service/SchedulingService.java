package com.scheduler.service;

import com.scheduler.engine.Scheduler;
import com.scheduler.model.Appointment;
import com.scheduler.repository.AppointmentRepository;
import com.scheduler.repository.ResourceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {

    private final AppointmentRepository appointmentRepo;
    private final ResourceRepository resourceRepo;

    // Constructor-based dependency injection (BEST PRACTICE)
    public SchedulingService(
            AppointmentRepository appointmentRepo,
            ResourceRepository resourceRepo
    ) {
        this.appointmentRepo = appointmentRepo;
        this.resourceRepo = resourceRepo;
    }

    // Save appointment to H2 database
    public void addAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    // Save resource to H2 database
    public void addResource(Resource resource) {
        resourceRepo.save(resource);
    }

    // Cancel appointment
    public void cancelAppointment(String appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }

    // Generate optimized schedule
    public Scheduler.ScheduleResult generateSchedule() {

        List<Appointment> appointments = appointmentRepo.findAll();
        List<Resource> resources = resourceRepo.findAll();

        // Reset resource availability before scheduling
        for (Resource r : resources) {
            r.setNextAvailableTime(r.getAvailableFrom());
        }

        return Scheduler.scheduleAppointments(appointments, resources);
    }
}

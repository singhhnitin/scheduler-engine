package com.scheduler.controller;

import com.scheduler.engine.Scheduler;
import com.scheduler.model.Appointment;
import com.scheduler.service.Resource;
import com.scheduler.service.SchedulingService;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    private final SchedulingService service;

    public SchedulerController(SchedulingService service) {
        this.service = service;
    }

    @PostMapping("/resource")
    public String addResource(@RequestBody Resource resource) {
        service.addResource(resource);
        return "Resource added successfully";
    }

    @PostMapping("/appointment")
    public String addAppointment(@RequestBody Appointment appointment) {
        service.addAppointment(appointment);
        return "Appointment added successfully";
    }

    @GetMapping
    public Scheduler.ScheduleResult getSchedule() {
        return service.generateSchedule();
    }

    @DeleteMapping("/appointment/{id}")
    public String cancelAppointment(@PathVariable String id) {
        service.cancelAppointment(id);
        return "Appointment cancelled successfully";
    }

@PostMapping("/simulate")
public Scheduler.ScheduleResult simulate(@RequestParam int count) {
    for (int i = 0; i < count; i++) {
        service.addAppointment(
            new Appointment(
                "SIM_" + i,
                0,
                500,
                5,
                1,
                i
            )
        );
    }
    return service.generateSchedule();
}
}
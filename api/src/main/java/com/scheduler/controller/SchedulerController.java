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

    private SchedulingService service = new SchedulingService();

    @PostMapping("/resource")
    public String addResource(@RequestBody Resource resource) {
        service.addResource(resource);
        return "Resource added";
    }

    @PostMapping("/appointment")
    public String addAppointment(@RequestBody Appointment appointment) {
        service.addAppointment(appointment);
        return "Appointment added";
    }

    @GetMapping
    public Scheduler.ScheduleResult getSchedule() {
        return service.generateSchedule();
    }

    @DeleteMapping("/appointment/{id}")
    public String cancelAppointment(@PathVariable String id) {
        service.cancelAppointment(id);
        return "Appointment cancelled";
    }
}

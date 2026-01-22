package com.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Appointment {

    @Id
    private String id;

    private int startTime;
    private int endTime;
    private int duration;
    private int priority;

    // NEW: required for walk-ins & fair ordering
    private int arrivalTime;

    // REQUIRED by JPA (DO NOT REMOVE)
    public Appointment() {
    }

    // Existing constructor (kept working)
    public Appointment(String id, int startTime, int endTime,
                       int duration, int priority) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.priority = priority;
        this.arrivalTime = startTime; // default arrival
    }

    // NEW constructor for walk-ins / simulation
    public Appointment(String id, int startTime, int endTime,
                       int duration, int priority, int arrivalTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }

    public String getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

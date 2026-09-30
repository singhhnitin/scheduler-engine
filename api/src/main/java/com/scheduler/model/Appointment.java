package com.scheduler.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private int startTime;
    private int endTime;
    private int duration;
    private int priority;

    // Used for fair ordering: same priority -> earlier arrival goes first
    private int arrivalTime;

    // Needed so Spring Data can create objects when reading from MongoDB
    public Appointment() {
    }

    public Appointment(String id, int startTime, int endTime,
                       int duration, int priority) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.priority = priority;
        this.arrivalTime = startTime; // default arrival
    }

    public Appointment(String id, int startTime, int endTime,
                       int duration, int priority, int arrivalTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getEndTime() { return endTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public int getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }
}

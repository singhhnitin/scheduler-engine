package model;

public class Appointment {

    private String id;
    private int startTime;
    private int endTime;
    private int duration;
    private int priority;

    // NEW: status to avoid duplicate scheduling
    private String status; // PENDING, ASSIGNED, REJECTED

    public Appointment(String id, int startTime, int endTime, int duration, int priority) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.priority = priority;
        this.status = "PENDING"; // default state
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

    // NEW getters/setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


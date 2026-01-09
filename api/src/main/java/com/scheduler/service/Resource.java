package com.scheduler.service;



public class Resource {
    private String id;
    private int availableFrom;
    private int availableTo;
    private int nextAvailableTime;

    public Resource(String id, int availableFrom, int availableTo) {
        this.id = id;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.nextAvailableTime = availableFrom;
    }

    public String getId() {
        return id;
    }

    public int getAvailableFrom() {
        return availableFrom;
    }

    public int getAvailableTo() {
        return availableTo;
    }

    public int getNextAvailableTime() {
        return nextAvailableTime;
    }

    public void setNextAvailableTime(int time) {
        this.nextAvailableTime = time;
    }
}

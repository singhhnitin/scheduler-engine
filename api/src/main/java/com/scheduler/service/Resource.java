package com.scheduler.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resource {

    @Id
    private String id;

    private int availableFrom;
    private int availableTo;
    private int nextAvailableTime;

    // ✅ REQUIRED by JPA
    public Resource() {
    }

    // Optional convenience constructor (safe to keep)
    public Resource(String id, int availableFrom, int availableTo) {
        this.id = id;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.nextAvailableTime = availableFrom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(int availableFrom) {
        this.availableFrom = availableFrom;
    }

    public int getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(int availableTo) {
        this.availableTo = availableTo;
    }

    public int getNextAvailableTime() {
        return nextAvailableTime;
    }

    public void setNextAvailableTime(int nextAvailableTime) {
        this.nextAvailableTime = nextAvailableTime;
    }
}

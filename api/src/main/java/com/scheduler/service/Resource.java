package com.scheduler.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resources")
public class Resource {

    @Id
    private String id;

    private int availableFrom;
    private int availableTo;
    private int nextAvailableTime;

    // Needed so Spring Data can create objects when reading from MongoDB
    public Resource() {
    }

    public Resource(String id, int availableFrom, int availableTo) {
        this.id = id;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.nextAvailableTime = availableFrom;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getAvailableFrom() { return availableFrom; }
    public void setAvailableFrom(int availableFrom) { this.availableFrom = availableFrom; }

    public int getAvailableTo() { return availableTo; }
    public void setAvailableTo(int availableTo) { this.availableTo = availableTo; }

    public int getNextAvailableTime() { return nextAvailableTime; }
    public void setNextAvailableTime(int nextAvailableTime) { this.nextAvailableTime = nextAvailableTime; }
}

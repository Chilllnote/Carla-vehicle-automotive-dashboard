package com.example;

import java.util.Date;

public class collisionObj {
    private String Type;
    private String actor;
    private Date timestamp;
    private double impactForce;

    public collisionObj(String eventType, String actor, Date timestamp, double impactForce) {
        this.Type = eventType;
        this.actor = actor;
        this.timestamp = timestamp;
        this.impactForce = impactForce;
    }

    public String getEventType() {
        return Type;
    }

    public String getActor() {
        return actor;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getImpactForce() {
        return impactForce;
    }

}

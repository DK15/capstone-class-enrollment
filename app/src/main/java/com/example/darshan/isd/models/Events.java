package com.example.darshan.isd.models;

public class Events {

    private String event_name;
    private String date;

    public Events(String event_name, String date) {
        this.event_name = event_name;
        this.date = date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDate() {
        return date;
    }
}

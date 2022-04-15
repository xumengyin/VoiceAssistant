package com.batman.baselibrary.event;

public class StatServiceEvent {

    private String event;

    public StatServiceEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}

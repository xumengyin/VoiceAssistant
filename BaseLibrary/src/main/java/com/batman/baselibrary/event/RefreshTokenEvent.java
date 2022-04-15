package com.batman.baselibrary.event;

public class RefreshTokenEvent {

    private String deviceToken;

    public RefreshTokenEvent(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }
}

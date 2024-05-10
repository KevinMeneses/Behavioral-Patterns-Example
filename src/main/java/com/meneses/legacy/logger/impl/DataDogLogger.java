package com.meneses.legacy.logger.impl;

import com.meneses.legacy.logger.Logger;

public class DataDogLogger implements Logger {
    @Override
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ": " + value + " to DataDog");
    }
}

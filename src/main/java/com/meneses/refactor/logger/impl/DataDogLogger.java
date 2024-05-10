package com.meneses.refactor.logger.impl;

import com.meneses.refactor.logger.Logger;

public class DataDogLogger implements Logger {
    @Override
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ": " + value + " to DataDog");
    }
}

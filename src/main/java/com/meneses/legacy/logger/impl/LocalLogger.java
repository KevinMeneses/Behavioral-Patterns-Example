package com.meneses.legacy.logger.impl;

import com.meneses.legacy.logger.Logger;

public class LocalLogger implements Logger {
    @Override
    public void logEvent(String event, String value) {
        System.out.println("Saving " + event + ": " + value + " in local file");
    }
}

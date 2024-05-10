package com.meneses.refactor.logger.impl;

import com.meneses.refactor.logger.Logger;

public class LocalLogger implements Logger {
    @Override
    public void logEvent(String event, String value) {
        System.out.println("Saving " + event + ": " + value + " in local file");
    }
}

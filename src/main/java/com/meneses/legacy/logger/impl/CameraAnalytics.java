package com.meneses.legacy.logger.impl;

import com.meneses.legacy.logger.Logger;

import java.util.ArrayList;

public class CameraAnalytics implements Logger {
    private final ArrayList<Logger> loggers = new ArrayList<>();

    @Override
    public void logEvent(String event, String value) {
        for (Logger logger : loggers) {
            logger.logEvent(event, value);
        }
    }

    public void addLogger(Logger logger) {
        loggers.add(logger);
    }

    public static CameraAnalytics create(int cameraType) {
        CameraAnalytics analytics = new CameraAnalytics();
        switch (cameraType) {
            case 1 -> analytics.addLogger(new LocalLogger());
            case 2 -> {
                analytics.addLogger(new LocalLogger());
                analytics.addLogger(new NewRelicLogger());
            }
            case 3 -> {
                analytics.addLogger(new LocalLogger());
                analytics.addLogger(new NewRelicLogger());
                analytics.addLogger(new DataDogLogger());
            }
        }

        return analytics;
    }
}

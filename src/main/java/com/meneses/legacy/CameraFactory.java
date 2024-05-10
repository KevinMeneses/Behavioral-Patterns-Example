package com.meneses.legacy;

import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.decorator.AudioRecorderLogger;
import com.meneses.legacy.camera.decorator.ImageRecorderLogger;
import com.meneses.legacy.camera.decorator.VideoRecorderLogger;
import com.meneses.legacy.camera.impl.CameraCache;
import com.meneses.legacy.camera.impl.CameraX;
import com.meneses.legacy.camera.impl.CameraY;
import com.meneses.legacy.camera.impl.CameraZ;
import com.meneses.legacy.logger.impl.CameraAnalytics;

public class CameraFactory {
    private final CameraService cameraService;
    private final CameraAnalytics cameraAnalytics;

    public CameraFactory(CameraService cameraService, CameraAnalytics cameraAnalytics) {
        this.cameraService = cameraService;
        this.cameraAnalytics = cameraAnalytics;
    }

    public Camera create(int type) {
        return switch (type) {
            case 1 -> new ImageRecorderLogger(new CameraX(cameraService), cameraAnalytics);
            case 2 -> new VideoRecorderLogger(new CameraY(cameraService), cameraAnalytics);
            case 3 -> new AudioRecorderLogger(new CameraCache(new CameraZ(cameraService)), cameraAnalytics);
            default -> null;
        };
    }
}

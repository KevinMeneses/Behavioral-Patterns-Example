package com.meneses.refactor;

import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.decorator.AudioRecorderLogger;
import com.meneses.refactor.camera.decorator.ImageRecorderLogger;
import com.meneses.refactor.camera.decorator.VideoRecorderLogger;
import com.meneses.refactor.camera.impl.CameraCache;
import com.meneses.refactor.camera.impl.CameraX;
import com.meneses.refactor.camera.impl.CameraY;
import com.meneses.refactor.camera.impl.CameraZ;
import com.meneses.refactor.logger.impl.CameraAnalytics;

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

package com.meneses.legacy.camera.decorator;

import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.ImageRecorder;
import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.legacy.logger.Logger;

import java.util.List;

public class ImageRecorderLogger implements Camera, ImageRecorder {
    private final ImageRecorder camera;
    protected final Logger logger;

    public ImageRecorderLogger(ImageRecorder camera, Logger logger) {
        this.camera = camera;
        this.logger = logger;
    }

    @Override
    public CameraFile getPhoto() {
        return camera.getPhoto();
    }

    @Override
    public List<CameraFileMetadata> getPhotosMetadata() {
        return camera.getPhotosMetadata();
    }

    @Override
    public Boolean takePhoto() {
        Boolean isSuccess = camera.takePhoto();
        logger.logEvent("takePhoto", isSuccess.toString());
        return isSuccess;
    }

    @Override
    public Boolean saveMetadata(CameraFileMetadata metadata) {
        return camera.saveMetadata(metadata);
    }
}

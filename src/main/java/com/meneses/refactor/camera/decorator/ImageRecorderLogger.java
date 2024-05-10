package com.meneses.refactor.camera.decorator;

import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.ImageRecorder;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.logger.Logger;

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

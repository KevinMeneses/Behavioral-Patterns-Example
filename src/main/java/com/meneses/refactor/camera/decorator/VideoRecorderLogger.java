package com.meneses.refactor.camera.decorator;

import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.ImageRecorder;
import com.meneses.refactor.camera.VideoRecorder;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.logger.Logger;

import java.util.List;

public class VideoRecorderLogger extends ImageRecorderLogger implements Camera, VideoRecorder {
    private final VideoRecorder camera;
    protected final Logger logger;

    public VideoRecorderLogger(VideoRecorder camera, Logger logger) {
        super((ImageRecorder) camera, logger);
        this.camera = camera;
        this.logger = logger;
    }

    @Override
    public CameraFile getVideo() {
        return camera.getVideo();
    }

    @Override
    public List<CameraFileMetadata> getVideosMetadata() {
        return camera.getVideosMetadata();
    }

    @Override
    public Boolean startVideoRecording() {
        Boolean isSuccess = camera.startVideoRecording();
        logger.logEvent("startVideoRecording", isSuccess.toString());
        return isSuccess;
    }

    @Override
    public Boolean stopVideoRecording() {
        Boolean isSuccess = camera.stopVideoRecording();
        logger.logEvent("stopVideoRecording", isSuccess.toString());
        return isSuccess;
    }
}

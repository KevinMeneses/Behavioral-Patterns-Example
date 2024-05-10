package com.meneses.legacy.camera.decorator;

import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.ImageRecorder;
import com.meneses.legacy.camera.VideoRecorder;
import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.legacy.logger.Logger;

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

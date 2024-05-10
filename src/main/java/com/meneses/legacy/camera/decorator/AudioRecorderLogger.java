package com.meneses.legacy.camera.decorator;

import com.meneses.legacy.camera.AudioRecorder;
import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.VideoRecorder;
import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.legacy.logger.Logger;

import java.util.List;

public class AudioRecorderLogger extends VideoRecorderLogger implements Camera, AudioRecorder {
    private final AudioRecorder camera;
    private final Logger logger;

    public AudioRecorderLogger(AudioRecorder camera, Logger logger) {
        super((VideoRecorder) camera, logger);
        this.camera = camera;
        this.logger = logger;
    }

    @Override
    public CameraFile getAudio() {
        return camera.getAudio();
    }

    @Override
    public List<CameraFileMetadata> getAudiosMetadata() {
        return camera.getAudiosMetadata();
    }

    @Override
    public Boolean startAudioRecording() {
        Boolean isSuccess = camera.startAudioRecording();
        logger.logEvent("startAudioRecording", isSuccess.toString());
        return isSuccess;
    }

    @Override
    public Boolean stopAudioRecording() {
        Boolean isSuccess = camera.stopAudioRecording();
        logger.logEvent("stopAudioRecording", isSuccess.toString());
        return isSuccess;
    }
}

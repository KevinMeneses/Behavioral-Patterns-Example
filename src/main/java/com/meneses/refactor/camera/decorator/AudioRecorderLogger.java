package com.meneses.refactor.camera.decorator;

import com.meneses.refactor.camera.AudioRecorder;
import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.VideoRecorder;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.logger.Logger;

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

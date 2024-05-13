package com.meneses.refactor.camera.decorator;

import com.meneses.refactor.camera.AudioRecorder;
import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.VideoRecorder;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.logger.Logger;
import com.meneses.refactor.media.MediaSubscriber;

import java.util.List;

public class AudioRecorderLogger extends VideoRecorderLogger implements Camera, AudioRecorder, FullCamera {
    private final FullCamera camera;
    private final Logger logger;

    public AudioRecorderLogger(FullCamera camera, Logger logger) {
        super(camera, logger);
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

    @Override
    public CameraMedia[] getAllMediaInfo() {
        return camera.getAllMediaInfo();
    }

    @Override
    public void subscribeToMedia(MediaSubscriber subscriber) {
        camera.subscribeToMedia(subscriber);
    }

    @Override
    public void unsubscribeToMedia(MediaSubscriber subscriber) {
        camera.unsubscribeToMedia(subscriber);
    }

    @Override
    public void startMediaStream(String mediaDir) throws RuntimeException {
        camera.startMediaStream(mediaDir);
    }
}

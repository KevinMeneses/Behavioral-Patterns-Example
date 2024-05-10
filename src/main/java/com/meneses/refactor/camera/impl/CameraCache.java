package com.meneses.refactor.camera.impl;


import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.media.MediaSubscriber;

import java.util.List;

public class CameraCache implements Camera, FullCamera {
    private final FullCamera camera;
    private static List<CameraFileMetadata> photosMetadata;
    private static List<CameraFileMetadata> videosMetadata;
    private static List<CameraFileMetadata> audiosMetadata;

    public CameraCache(FullCamera camera) {
        this.camera = camera;
    }

    @Override
    public CameraFile getAudio() {
        return camera.getAudio();
    }

    @Override
    public List<CameraFileMetadata> getAudiosMetadata() {
        if (audiosMetadata == null) {
            audiosMetadata = camera.getAudiosMetadata();
        }
        return audiosMetadata;
    }

    @Override
    public Boolean startAudioRecording() {
        return camera.startAudioRecording();
    }

    @Override
    public Boolean stopAudioRecording() {
        return camera.stopAudioRecording();
    }

    @Override
    public CameraFile getPhoto() {
        return camera.getPhoto();
    }

    @Override
    public List<CameraFileMetadata> getPhotosMetadata() {
        if (photosMetadata == null) {
            photosMetadata = camera.getPhotosMetadata();
        }
        return photosMetadata;
    }

    @Override
    public Boolean takePhoto() {
        return camera.takePhoto();
    }

    @Override
    public Boolean saveMetadata(CameraFileMetadata metadata) {
        return camera.saveMetadata(metadata);
    }

    @Override
    public CameraFile getVideo() {
        return camera.getVideo();
    }

    @Override
    public List<CameraFileMetadata> getVideosMetadata() {
        if (videosMetadata == null) {
            videosMetadata = camera.getVideosMetadata();
        }
        return videosMetadata;
    }

    @Override
    public Boolean startVideoRecording() {
        return camera.startVideoRecording();
    }

    @Override
    public Boolean stopVideoRecording() {
        return camera.stopVideoRecording();
    }

    @Override
    public void subscribeToMedia(MediaSubscriber subscriber) {
        camera.subscribeToMedia(subscriber);
    }

    @Override
    public void unsubscribeToMedia(MediaSubscriber subscriber) {
        camera.subscribeToMedia(subscriber);
    }

    @Override
    public void startMediaStream(String mediaDir) throws RuntimeException {
        camera.startMediaStream(mediaDir);
    }
}

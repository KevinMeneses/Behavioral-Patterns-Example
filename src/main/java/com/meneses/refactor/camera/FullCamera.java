package com.meneses.refactor.camera;

import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.media.MediaSubscriber;

public interface FullCamera extends Camera, ImageRecorder, VideoRecorder, AudioRecorder {
    CameraMedia[] getAllMediaInfo();
    void subscribeToMedia(MediaSubscriber subscriber);
    void unsubscribeToMedia(MediaSubscriber subscriber);
    void startMediaStream(String mediaDir) throws RuntimeException;
}

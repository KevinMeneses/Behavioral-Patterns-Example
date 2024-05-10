package com.meneses.refactor.camera;

import com.meneses.refactor.media.MediaSubscriber;

public interface FullCamera extends Camera, ImageRecorder, VideoRecorder, AudioRecorder {
    void subscribeToMedia(MediaSubscriber subscriber);
    void unsubscribeToMedia(MediaSubscriber subscriber);
    void startMediaStream(String mediaDir) throws RuntimeException;
}

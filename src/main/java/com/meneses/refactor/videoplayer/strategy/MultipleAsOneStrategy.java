package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.impl.CameraZ;
import com.meneses.refactor.media.MediaSubscriber;
import com.meneses.refactor.videoplayer.VideoPlayer;

public class MultipleAsOneStrategy implements PlaybackStrategy, MediaSubscriber {
    public final VideoPlayer videoPlayer;
    private final CameraZ camera;
    private String[] mediaDirs;
    private int currentMediaIndex = 0;

    public MultipleAsOneStrategy(VideoPlayer videoPlayer, CameraZ camera) {
        this.videoPlayer = videoPlayer;
        this.camera = camera;
    }

    public void setMedia(String[] mediaDir) {
        camera.subscribeToMedia(this);
        this.mediaDirs = mediaDir;
    }

    @Override
    public void updateBuffer(byte[] bytes) {
        videoPlayer.startPlayback(bytes);
        videoPlayer.setListener(() -> {
            if (currentMediaIndex < mediaDirs.length - 1) {
                currentMediaIndex++;
                startPlayback();
            }
        });
    }

    @Override
    public void startPlayback() {
        if (videoPlayer.isMediaLoaded()) {
            videoPlayer.resumePlayback();
        } else {
            try {
                camera.startMediaStream(mediaDirs[currentMediaIndex]);
            } catch (RuntimeException e) {
                camera.unsubscribeToMedia(this);
            }
        }
    }

    @Override
    public void pausePlayback() {
        videoPlayer.pausePlayback();
    }

    @Override
    public void stopPlayback() {
        videoPlayer.stopPlayback();
        currentMediaIndex = 0;
    }

    @Override
    public void forward() {
        if (currentMediaIndex < mediaDirs.length - 1) {
            currentMediaIndex++;
            videoPlayer.stopPlayback();
            startPlayback();
        }
    }

    @Override
    public void rewind() {
        if (currentMediaIndex > 0) {
            currentMediaIndex--;
            videoPlayer.stopPlayback();
            startPlayback();
        }
    }

    @Override
    public void setProgress(int second) {

    }
}

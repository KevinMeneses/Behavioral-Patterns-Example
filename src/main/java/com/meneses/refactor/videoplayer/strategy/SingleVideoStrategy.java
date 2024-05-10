package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.impl.CameraZ;
import com.meneses.refactor.media.MediaSubscriber;
import com.meneses.refactor.videoplayer.VideoPlayer;

public class SingleVideoStrategy implements PlaybackStrategy, MediaSubscriber {
    public final VideoPlayer videoPlayer;
    private final CameraZ camera;
    private String mediaDir;

    public SingleVideoStrategy(VideoPlayer videoPlayer, CameraZ camera) {
        this.videoPlayer = videoPlayer;
        this.camera = camera;
    }

    public void setMedia(String mediaDir) {
        camera.subscribeToMedia(this);
        this.mediaDir = mediaDir;
    }

    @Override
    public void updateBuffer(byte[] bytes) {
        videoPlayer.startPlayback(bytes);
    }

    @Override
    public void startPlayback() {
        if (videoPlayer.isMediaLoaded()) {
            videoPlayer.resumePlayback();
        } else {
            try {
                camera.startMediaStream(mediaDir);
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
    }

    @Override
    public void forward() {
        videoPlayer.setProgress(videoPlayer.getCurrentPosition() + 5);
    }

    @Override
    public void rewind() {
        videoPlayer.setProgress(videoPlayer.getCurrentPosition() - 5);
    }

    @Override
    public void setProgress(int second) {
        videoPlayer.setProgress(second);
    }
}

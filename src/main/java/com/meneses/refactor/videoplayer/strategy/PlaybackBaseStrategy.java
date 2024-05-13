package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.media.MediaSubscriber;
import com.meneses.refactor.videoplayer.VideoPlayer;

public abstract class PlaybackBaseStrategy implements PlaybackStrategy, MediaSubscriber {
    public final VideoPlayer videoPlayer;
    protected final FullCamera camera;
    protected boolean stoppedByUser = false;

    protected PlaybackBaseStrategy(VideoPlayer videoPlayer, FullCamera camera) {
        this.videoPlayer = videoPlayer;
        this.camera = camera;
        camera.subscribeToMedia(this);
    }

    @Override
    public void pausePlayback() {
        videoPlayer.pausePlayback();
    }

    @Override
    public void stopPlayback() {
        stoppedByUser = true;
        videoPlayer.stopPlayback();
    }

    @Override
    public void setProgress(int second) {
        videoPlayer.setProgress(second);
    }

    @Override
    public void updateBuffer(byte[] bytes) {
        System.out.println("Video buffer reloaded");
        videoPlayer.startPlayback(bytes);
    }
}

package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.videoplayer.VideoPlayer;

public class SingleVideoStrategy extends PlaybackBaseStrategy {
    private CameraMedia media;
    private boolean stoppedByUser = false;

    public SingleVideoStrategy(VideoPlayer videoPlayer, FullCamera camera) {
        super(videoPlayer, camera);
        setOnFinishedListener();
    }

    private void setOnFinishedListener() {
        videoPlayer.setEventListener(() -> {
            // on finished
            if (stoppedByUser) {
                stoppedByUser = false;
                return;
            }
            System.out.println("Playback finished");
            videoPlayer.stopPlayback();
        });
    }

    public void setMedia(CameraMedia media) {
        camera.subscribeToMedia(this);
        this.media = media;
    }

    @Override
    public void startPlayback() {
        if (videoPlayer.isMediaLoaded()) {
            videoPlayer.resumePlayback();
        } else {
            try {
                System.out.println("Playback started");
                camera.startMediaStream(media.dir);
            } catch (RuntimeException e) {
                camera.unsubscribeToMedia(this);
            }
        }
    }

    @Override
    public void forward() {
        System.out.println("Forward 5 seconds");
        videoPlayer.setProgress(videoPlayer.getCurrentSecond() + 5);
    }

    @Override
    public void rewind() {
        System.out.println("Rewind 5 seconds");
        videoPlayer.setProgress(videoPlayer.getCurrentSecond() - 5);
    }
}

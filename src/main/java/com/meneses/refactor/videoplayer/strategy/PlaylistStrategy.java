package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.videoplayer.VideoPlayer;

public class PlaylistStrategy extends PlaybackBaseStrategy {
    private CameraMedia[] medias;
    private int currentMediaIndex = 0;
    private boolean stoppedByUser = false;

    public PlaylistStrategy(VideoPlayer videoPlayer, FullCamera camera) {
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
            if (currentMediaIndex < medias.length - 1) {
                currentMediaIndex++;
                startMediaStream();
            } else {
                System.out.println("Playback finished");
                videoPlayer.stopPlayback();
                currentMediaIndex = 0;
            }
        });
    }

    public void setMedias(CameraMedia[] medias) {
        camera.subscribeToMedia(this);
        this.medias = medias;
    }

    @Override
    public void startPlayback() {
        if (videoPlayer.isMediaLoaded()) {
            videoPlayer.resumePlayback();
        } else {
            System.out.println("Playback started");
            startMediaStream();
        }
    }

    private void startMediaStream() {
        try {
            camera.startMediaStream(medias[currentMediaIndex].dir);
        } catch (RuntimeException e) {
            camera.unsubscribeToMedia(this);
        }
    }

    @Override
    public void stopPlayback() {
        super.stopPlayback();
        currentMediaIndex = 0;
    }

    @Override
    public void forward() {
        if (currentMediaIndex < medias.length - 1) {
            currentMediaIndex++;
            super.stopPlayback();
            System.out.println("Next video");
            startMediaStream();
        }
    }

    @Override
    public void rewind() {
        if (currentMediaIndex > 0) {
            currentMediaIndex--;
            super.stopPlayback();
            System.out.println("Previous video");
            startMediaStream();
        }
    }
}

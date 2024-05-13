package com.meneses.refactor.videoplayer.strategy;

import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.videoplayer.VideoPlayer;

public class MultipleAsOneStrategy extends PlaybackBaseStrategy {
    private CameraMedia[] medias;
    private int currentMediaIndex = 0;

    public MultipleAsOneStrategy(VideoPlayer videoPlayer, FullCamera camera) {
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
        System.out.println("Forward 10 seconds");
        setProgress(videoPlayer.getCurrentSecond() + 10);
    }

    @Override
    public void rewind() {
        System.out.println("Rewind 10 seconds");
        setProgress(videoPlayer.getCurrentSecond() - 10);
    }

    @Override
    public void setProgress(int second) {
        int currentDuration = 0;
        int tmpCurrentMediaIndex = 0;

        for (CameraMedia media: medias) {
            currentDuration += media.duration;
            if (second > currentDuration) {
                tmpCurrentMediaIndex++;
                System.out.println("Next video section");
            } else {
                if (currentMediaIndex > tmpCurrentMediaIndex) {
                    System.out.println("Previous video section");
                    currentMediaIndex = tmpCurrentMediaIndex;
                } else {
                    videoPlayer.setProgress(second);
                    return;
                }
            }
        }

        super.stopPlayback();
        startMediaStream();
        videoPlayer.setProgress(second);
    }
}

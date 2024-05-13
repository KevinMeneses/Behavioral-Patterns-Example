package com.meneses.legacy.videoplayer;

import com.meneses.legacy.camera.FullCamera;

public class VideoPlayerView {
    private final VideoPlayer videoPlayer;
    private final FullCamera camera;
    private Boolean isLocked = false;
    public VideoPlayerView(VideoPlayer videoPlayer, FullCamera camera) {
        this.videoPlayer = videoPlayer;
        this.camera = camera;
    }

    public void showLockedScreen() {
        System.out.println("----Screen Locked----");
    }

    public void hideLockedScreen() {
        System.out.println("----Screen unlocked----");
    }

    public void clickPlay() {
        if (isLocked) {
            showLockedScreen();
            return;
        }
        videoPlayer.startPlayback();
    }

    public void clickStop() {
        if (isLocked) {
            showLockedScreen();
            return;
        }
        videoPlayer.stopPlayback();
        toggleLockButton(false);
    }

    public void clickLock() {
        if (isLocked) {
            hideLockedScreen();
            isLocked = false;
        } else {
            showLockedScreen();
            isLocked = true;
        }
    }

    public void clickNext() {
        if (isLocked) {
            showLockedScreen();
            return;
        }
        videoPlayer.forward();
    }

    public void clickPrevious() {
        if (isLocked) {
            showLockedScreen();
            return;
        }
        videoPlayer.rewind();
    }

    public void touchProgressBar(int second) {
        if (isLocked) {
            showLockedScreen();
            return;
        }
        videoPlayer.setProgress(second);
    }

    public void toggleLockButton(Boolean enabled) {
        if (enabled) {
            System.out.println("Lock button enabled");
        } else {
            System.out.println("Lock button disabled");
        }
    }
}

package com.meneses.legacy.videoplayer;

import com.meneses.legacy.camera.FullCamera;

public class VideoPlayerView {
    private final VideoPlayer videoPlayer;

    public VideoPlayerView(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    private Boolean isLocked = false;
    private Boolean isPlaying = false;

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

        if (videoPlayer.isMediaLoaded()) {
            if (isPlaying) {
                isPlaying = false;
                videoPlayer.pausePlayback();
            } else {
                isPlaying = true;
                videoPlayer.resumePlayback();
            }
        } else {
            videoPlayer.startMediaStream();
        }
    }

    public void clickStop() {
        if (isLocked) {
            showLockedScreen();
            return;
        }

        isPlaying = false;
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

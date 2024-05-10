package com.meneses.refactor.videoplayer.state;

import com.meneses.refactor.videoplayer.VideoPlayerView;

public class Locked implements VideoPlayerState {

    private final VideoPlayerView videoPlayerView;
    private Boolean isLockScreenShowing = false;

    public Locked(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
    }

    private void handleLockScreenTouch() {
        if (isLockScreenShowing) {
            videoPlayerView.hideLockedScreen();
            isLockScreenShowing = false;
        } else {
            videoPlayerView.showLockedScreen();
            isLockScreenShowing = true;
        }
    }

    @Override
    public void clickPlay() {
        handleLockScreenTouch();
    }

    @Override
    public void clickStop() {
        handleLockScreenTouch();
    }

    @Override
    public void clickLock() {
        // unlock
        videoPlayerView.hideLockedScreen();
        videoPlayerView.setState(new Playing(videoPlayerView));
    }

    @Override
    public void clickNext() {
        handleLockScreenTouch();
    }

    @Override
    public void clickPrevious() {
        handleLockScreenTouch();
    }

    @Override
    public void touchProgressBar(int second) {
        handleLockScreenTouch();
    }
}

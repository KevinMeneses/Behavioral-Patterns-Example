package com.meneses.refactor.videoplayer.state;

import com.meneses.refactor.videoplayer.VideoPlayerView;

public class Locked implements VideoPlayerState {
    private final VideoPlayerView videoPlayerView;
    public Locked(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
    }

    @Override
    public void clickPlay() {
        videoPlayerView.showLockedScreen();
    }

    @Override
    public void clickStop() {
        videoPlayerView.showLockedScreen();
    }

    @Override
    public void clickLock() {
        videoPlayerView.hideLockedScreen();
        videoPlayerView.setState(new Playing(videoPlayerView));
    }

    @Override
    public void clickNext() {
        videoPlayerView.showLockedScreen();
    }

    @Override
    public void clickPrevious() {
        videoPlayerView.showLockedScreen();
    }

    @Override
    public void touchProgressBar(int second) {
        videoPlayerView.showLockedScreen();
    }
}

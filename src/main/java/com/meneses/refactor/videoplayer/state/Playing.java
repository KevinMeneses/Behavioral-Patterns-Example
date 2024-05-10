package com.meneses.refactor.videoplayer.state;

import com.meneses.refactor.videoplayer.VideoPlayerView;

public class Playing implements VideoPlayerState {
    private final VideoPlayerView videoPlayerView;
    private Boolean isPlaying = true;

    public Playing(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
    }

    @Override
    public void clickPlay() {
        if (isPlaying) {
            videoPlayerView.strategy.pausePlayback();
            isPlaying = false;
        } else {
            videoPlayerView.strategy.startPlayback();
            isPlaying = true;
        }
    }

    @Override
    public void clickStop() {
        videoPlayerView.strategy.stopPlayback();
        videoPlayerView.setState(new Stopped(videoPlayerView));
        videoPlayerView.toggleLockButton(false);
    }

    @Override
    public void clickLock() {
        videoPlayerView.showLockedScreen();
        videoPlayerView.setState(new Locked(videoPlayerView));
    }

    @Override
    public void clickNext() {
        videoPlayerView.strategy.forward();
    }

    @Override
    public void clickPrevious() {
        videoPlayerView.strategy.rewind();
    }

    @Override
    public void touchProgressBar(int second) {
        videoPlayerView.strategy.setProgress(second);
    }
}

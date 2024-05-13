package com.meneses.refactor.videoplayer.state;

import com.meneses.refactor.videoplayer.VideoPlayerView;

public class Stopped implements VideoPlayerState {
    private final VideoPlayerView videoPlayerView;

    public Stopped(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
    }

    @Override
    public void clickPlay() {
        videoPlayerView.strategy.startPlayback();
        videoPlayerView.setState(new Playing(videoPlayerView));
        videoPlayerView.toggleLockButton(true);
    }

    @Override
    public void clickStop() {
        System.out.println("Progress reset");
        videoPlayerView.strategy.setProgress(0);
    }

    @Override
    public void clickLock() {
        System.out.println("Option not available while stopped");
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

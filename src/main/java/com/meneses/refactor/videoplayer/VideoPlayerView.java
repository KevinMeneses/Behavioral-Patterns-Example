package com.meneses.refactor.videoplayer;

import com.meneses.refactor.videoplayer.state.VideoPlayerState;
import com.meneses.refactor.videoplayer.state.Stopped;
import com.meneses.refactor.videoplayer.strategy.PlaybackStrategy;

public class VideoPlayerView {
    public final PlaybackStrategy strategy;
    private VideoPlayerState state = new Stopped(this);

    public VideoPlayerView(PlaybackStrategy strategy) {
        this.strategy = strategy;
    }

    public void setState(VideoPlayerState state) {
        this.state = state;
    }

    public void showLockedScreen() {
        System.out.println("----Screen Locked----");
    }

    public void hideLockedScreen() {
        System.out.println("----Screen unlocked----");
    }

    public void clickPlay() {
        state.clickPlay();
    }

    public void clickStop() {
        state.clickStop();
    }

    public void clickLock() {
        state.clickLock();
    }

    public void clickNext() {
        state.clickNext();
    }

    public void clickPrevious() {
        state.clickPrevious();
    }

    public void touchProgressBar(int second) {
        state.touchProgressBar(second);
    }

    public void toggleLockButton(Boolean enabled) {
        if (enabled) {
            System.out.println("Lock button enabled");
        } else {
            System.out.println("Lock button disabled");
        }
    }
}

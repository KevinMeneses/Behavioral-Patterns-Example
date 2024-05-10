package com.meneses.refactor.videoplayer.strategy;

public interface PlaybackStrategy {
    void startPlayback();
    void pausePlayback();
    void stopPlayback();
    void forward();
    void rewind();
    void setProgress(int second);
}

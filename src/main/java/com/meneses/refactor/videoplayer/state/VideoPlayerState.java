package com.meneses.refactor.videoplayer.state;

public interface VideoPlayerState {
    void clickPlay();
    void clickStop();
    void clickLock();
    void clickNext();
    void clickPrevious();
    void touchProgressBar(int second);
}

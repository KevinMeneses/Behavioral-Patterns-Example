package com.meneses.refactor.videoplayer;

import java.util.concurrent.atomic.AtomicBoolean;

public class VideoPlayer {
    private byte[] buffer;
    private int currentPosition = 0;
    private final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private Listener listener = () -> {};

    public Boolean isMediaLoaded() {
        return buffer != null;
    }

    public void startPlayback(byte[] bytes) {
        buffer = bytes;
        isPlaying.set(true);
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (; currentPosition < buffer.length - 1; currentPosition++ ) {
                    System.out.println("frame " + buffer[currentPosition]);
                    if (!isPlaying.get()) break;
                }
                buffer = null;
                listener.onFinished();
            }
        };
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void pausePlayback() {
        isPlaying.set(false);
    }

    public void resumePlayback() {
        startPlayback(buffer);
    }

    public void stopPlayback() {
        isPlaying.set(false);
        buffer = null;
        currentPosition = 0;
    }

    public void setProgress(int second) {
        currentPosition = second;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onFinished();
    }
}

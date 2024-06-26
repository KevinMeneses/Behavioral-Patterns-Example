package com.meneses.refactor.videoplayer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class VideoPlayer {
    private byte[] buffer;
    private final AtomicInteger currentSecond = new AtomicInteger(0);
    private final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private Thread playbackThread = new Thread();
    private Listener listener = () -> {
    };

    public Boolean isMediaLoaded() {
        return buffer != null;
    }

    public void startPlayback(byte[] bytes) {
        buffer = bytes;
        isPlaying.set(true);
        playbackThread = null;
        playbackThread = new Thread(() -> {
            try {
                while (currentSecond.get() < buffer.length && !playbackThread.isInterrupted()) {
                    if (!isPlaying.get()) continue;
                    System.out.println("frame " + buffer[currentSecond.getAndIncrement()]);
                    Thread.sleep(1000);
                }
            } catch (Exception ignored) {
                // no-op
            }

            buffer = null;
            isPlaying.set(false);
            currentSecond.set(0);
            listener.onFinished();
        });

        playbackThread.start();
    }

    public int getCurrentSecond() {
        return currentSecond.get();
    }

    public void pausePlayback() {
        System.out.println("Playback paused");
        isPlaying.set(false);
    }

    public void resumePlayback() {
        System.out.println("Playback resumed from pause");
        isPlaying.set(true);
    }

    public void stopPlayback() {
        System.out.println("Playback stopped");
        playbackThread.interrupt();
        isPlaying.set(false);
        buffer = null;
        currentSecond.set(0);
    }

    public void setProgress(int second) {
        currentSecond.set(second);
    }

    public void setEventListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onFinished();
    }
}

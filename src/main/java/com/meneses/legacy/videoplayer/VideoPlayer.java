package com.meneses.legacy.videoplayer;

import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraMedia;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class VideoPlayer {
    private final FullCamera camera;
    private byte[] buffer;
    private final AtomicInteger currentSecond = new AtomicInteger(0);
    private final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private Thread playbackThread = new Thread();
    private Listener listener = () -> {
    };

    private CameraMedia media;
    private CameraMedia[] medias;
    private int currentMediaIndex = 0;
    private PlaybackType playbackType;

    public VideoPlayer(FullCamera camera) {
        this.camera = camera;
    }

    public Boolean isMediaLoaded() {
        return buffer != null;
    }

    public void setMedia(CameraMedia media) {
        this.media = media;
        playbackType = PlaybackType.SINGLE_VIDEO;
    }

    public void setMedias(CameraMedia[] medias, Boolean asOne) {
        this.medias = medias;
        if (asOne) {
            playbackType = PlaybackType.MULTIPLE_AS_ONE;
        } else {
            playbackType = PlaybackType.PLAYLIST;
        }
    }

    public void startPlayback() {
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

    public void forward() {
        if (playbackType == PlaybackType.SINGLE_VIDEO) {
            System.out.println("Forward 5 seconds");
            currentSecond.set(currentSecond.get() + 5);
        }
        if (playbackType == PlaybackType.PLAYLIST) {
            System.out.println("Next video");
            currentMediaIndex++;
        }
        if (playbackType == PlaybackType.MULTIPLE_AS_ONE) {
            System.out.println("Forward 10 seconds");

        }
    }

    public void rewind() {
        if (playbackType == PlaybackType.SINGLE_VIDEO) {
            System.out.println("Rewind 5 seconds");
            currentSecond.set(currentSecond.get() - 5);
        }
        if (playbackType == PlaybackType.PLAYLIST) {
            System.out.println("Previous video");
            currentMediaIndex--;
        }
        if (playbackType == PlaybackType.MULTIPLE_AS_ONE) {
            System.out.println("Rewind 10 seconds");

        }
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

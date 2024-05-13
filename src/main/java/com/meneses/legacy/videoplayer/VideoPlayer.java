package com.meneses.legacy.videoplayer;

import com.meneses.legacy.camera.FullCamera;
import com.meneses.legacy.camera.model.CameraMedia;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class VideoPlayer {
    private final FullCamera camera;
    private byte[] buffer;
    private final AtomicInteger currentSecond = new AtomicInteger(0);
    private final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private Thread playbackThread = new Thread();
    private CameraMedia media;
    private CameraMedia[] medias;
    private int currentMediaIndex = 0;
    private PlaybackType playbackType;
    private boolean stoppedByUser = false;

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

    private void startPlayback(byte[] bytes) {
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
            onFinished();
        });

        playbackThread.start();
    }

    private void onFinished() {
        if (stoppedByUser) {
            stoppedByUser = false;
            return;
        }
        if (playbackType == PlaybackType.SINGLE_VIDEO) {
            System.out.println("Playback finished");
            stopPlayback();
            return;
        }
        if (currentMediaIndex < medias.length - 1) {
            currentMediaIndex++;
            startMediaStream();
        } else {
            System.out.println("Playback finished");
            stopPlayback();
        }
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
        if (!isMediaLoaded()) {
            System.out.println("Progress reset");
        } else {
            System.out.println("Playback stopped");
        }

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
            setProgressForMultipleAsOne(currentSecond.get() + 10);
        }
    }

    public void rewind() {
        if (playbackType == PlaybackType.SINGLE_VIDEO) {
            System.out.println("Rewind 5 seconds");
            setProgress(currentSecond.get() - 5);
        }
        if (playbackType == PlaybackType.PLAYLIST) {
            System.out.println("Previous video");
            currentMediaIndex--;
        }
        if (playbackType == PlaybackType.MULTIPLE_AS_ONE) {
            System.out.println("Rewind 10 seconds");
            setProgressForMultipleAsOne(currentSecond.get() - 10);
        }
    }

    public void setProgress(int second) {
        currentSecond.set(second);
    }

    public void setProgressForMultipleAsOne(int second) {
        int currentDuration = 0;
        int tmpCurrentMediaIndex = 0;

        for (CameraMedia media : medias) {
            currentDuration += media.duration;
            if (second > currentDuration) {
                tmpCurrentMediaIndex++;
                System.out.println("Next video section");
            } else {
                if (currentMediaIndex > tmpCurrentMediaIndex) {
                    System.out.println("Previous video section");
                    currentMediaIndex = tmpCurrentMediaIndex;
                } else {
                    setProgress(second);
                    return;
                }
            }
        }

        stopPlayback();
        startMediaStream();
        setProgress(second);
    }

    public void startMediaStream() {
        try {
            byte [] mediaBytes = null;
            while (mediaBytes == null) {
                if (playbackType == PlaybackType.SINGLE_VIDEO) {
                    mediaBytes = camera.getMediaBytes(media.dir);
                } else {
                    mediaBytes = camera.getMediaBytes(medias[currentMediaIndex].dir);
                }
                Thread.sleep(1000);
            }
            startPlayback(mediaBytes);
        } catch (InterruptedException ignored) {

        }
    }
}

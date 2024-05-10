package com.meneses.refactor.camera;

import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.media.MediaSubscriber;

import java.util.List;

public interface VideoRecorder extends Camera {
    CameraFile getVideo();
    List<CameraFileMetadata> getVideosMetadata();
    Boolean startVideoRecording();
    Boolean stopVideoRecording();
}

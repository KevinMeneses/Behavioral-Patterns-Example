package com.meneses.legacy.camera;

import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;

import java.util.List;

public interface VideoRecorder extends Camera {
    CameraFile getVideo();
    List<CameraFileMetadata> getVideosMetadata();
    Boolean startVideoRecording();
    Boolean stopVideoRecording();
}

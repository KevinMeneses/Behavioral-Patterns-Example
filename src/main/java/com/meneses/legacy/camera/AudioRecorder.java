package com.meneses.legacy.camera;

import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;

import java.util.List;

public interface AudioRecorder extends Camera {
    CameraFile getAudio();
    List<CameraFileMetadata> getAudiosMetadata();
    Boolean startAudioRecording();
    Boolean stopAudioRecording();
}

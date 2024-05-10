package com.meneses.refactor.camera;

import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;

import java.util.List;

public interface AudioRecorder extends Camera {
    CameraFile getAudio();
    List<CameraFileMetadata> getAudiosMetadata();
    Boolean startAudioRecording();
    Boolean stopAudioRecording();
}

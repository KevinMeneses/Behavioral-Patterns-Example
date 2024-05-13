package com.meneses.legacy.camera;

import com.meneses.legacy.camera.model.CameraMedia;

public interface FullCamera extends Camera, ImageRecorder, VideoRecorder, AudioRecorder {
    CameraMedia[] getAllMediaInfo();
}

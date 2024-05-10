package com.meneses.legacy.camera;

import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;

import java.util.List;

public interface ImageRecorder extends Camera {
    CameraFile getPhoto();
    List<CameraFileMetadata> getPhotosMetadata();
    Boolean takePhoto();
    Boolean saveMetadata(CameraFileMetadata metadata);
}

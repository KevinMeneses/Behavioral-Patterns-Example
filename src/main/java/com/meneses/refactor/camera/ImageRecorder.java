package com.meneses.refactor.camera;

import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;

import java.util.List;

public interface ImageRecorder extends Camera {
    CameraFile getPhoto();
    List<CameraFileMetadata> getPhotosMetadata();
    Boolean takePhoto();
    Boolean saveMetadata(CameraFileMetadata metadata);
}

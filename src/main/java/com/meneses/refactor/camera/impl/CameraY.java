package com.meneses.refactor.camera.impl;

import com.meneses.refactor.CameraService;
import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.ImageRecorder;
import com.meneses.refactor.camera.VideoRecorder;
import com.meneses.refactor.camera.model.CameraCommand;
import com.meneses.refactor.camera.model.CameraCommandResult;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;

import java.util.List;

public class CameraY implements Camera, ImageRecorder, VideoRecorder {
    private final CameraService cameraService;
    public CameraY(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @Override
    public CameraFile getPhoto() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setInformation("file")
                .setType("photo")
                .setFetchSize(10)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    private CameraFile parseToFile(CameraCommandResult result) {
        return new CameraFile();
    }

    @Override
    public List<CameraFileMetadata> getPhotosMetadata() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(2)
                .setInformation("metadata")
                .setType("photo")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToMetadataList(result);
    }

    private List<CameraFileMetadata> parseToMetadataList(CameraCommandResult result) {
        return List.of(new CameraFileMetadata());
    }

    @Override
    public Boolean takePhoto() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(3)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    private Boolean parseToBoolean(CameraCommandResult result) {
        return true;
    }

    @Override
    public CameraFile getVideo() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setFetchSize(10)
                .setInformation("file")
                .setType("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    @Override
    public List<CameraFileMetadata> getVideosMetadata() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(2)
                .setInformation("metadata")
                .setType("videos")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToMetadataList(result);
    }

    @Override
    public Boolean startVideoRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(4)
                .setInformation("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    @Override
    public Boolean stopVideoRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(5)
                .setInformation("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }


    @Override
    public Boolean saveMetadata(CameraFileMetadata metadata) {
        String information = parseToString(metadata);
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(10)
                .setInformation(information)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    private String parseToString(CameraFileMetadata metadata) {
        return "";
    }
}

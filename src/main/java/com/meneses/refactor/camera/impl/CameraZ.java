package com.meneses.refactor.camera.impl;

import com.meneses.refactor.CameraService;
import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.FullCamera;
import com.meneses.refactor.camera.model.CameraCommand;
import com.meneses.refactor.camera.model.CameraCommandResult;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.media.MediaPublisher;
import com.meneses.refactor.media.MediaSubscriber;

import java.util.List;

public class CameraZ implements Camera, FullCamera {
    private final CameraService cameraService;
    private final MediaPublisher mediaPublisher = new MediaPublisher();

    public CameraZ(CameraService cameraService) {
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
                .setOffset(3)
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
    public CameraFile getAudio() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setFetchSize(10)
                .setInformation("file")
                .setType("audio")
                .setOffset(3)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    @Override
    public List<CameraFileMetadata> getAudiosMetadata() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(2)
                .setInformation("metadata")
                .setType("audios")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToMetadataList(result);
    }

    @Override
    public Boolean startAudioRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(6)
                .setInformation("audio")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    @Override
    public Boolean stopAudioRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(7)
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

    @Override
    public void subscribeToMedia(MediaSubscriber subscriber) {
        mediaPublisher.subscribe(subscriber);
    }

    @Override
    public void unsubscribeToMedia(MediaSubscriber subscriber) {
        mediaPublisher.unsubscribe(subscriber);
    }

    @Override
    public void startMediaStream(String mediaDir) throws RuntimeException {
        byte[][] bytes = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };

        for (byte[] data : bytes) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(5000);
                        mediaPublisher.notifyChanges(data);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }
}

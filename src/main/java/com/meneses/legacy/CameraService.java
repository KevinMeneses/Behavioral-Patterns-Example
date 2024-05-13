package com.meneses.legacy;


import com.meneses.legacy.camera.model.CameraCommand;
import com.meneses.legacy.camera.model.CameraCommandResult;

import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

public class CameraService {

    private static CameraService instance;

    private final Map<String, byte[]> mediaBytes = Map.of(
            "/media/1.mp4", new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            "/media/2.mp4", new byte[] {11, 12, 13, 14, 15, 16, 17, 18, 19},
            "/media/3.mp4", new byte[] {20, 21, 22, 23, 24, 25, 26, 27},
            "/media/4.mp4", new byte[] {28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40}
    );

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token = "";

    public CameraCommandResult sendCommand(CameraCommand command) {
        return new CameraCommandResult();
    }

    static CameraService getInstance() {
        if (instance == null) {
            instance = new CameraService();
        }

        return instance;
    }

    public byte[] getMediaBytes(String mediaDir) {
        if (new Random(10).nextBoolean()) {
            return mediaBytes.get(mediaDir);
        } else {
            return null;
        }
    }
}

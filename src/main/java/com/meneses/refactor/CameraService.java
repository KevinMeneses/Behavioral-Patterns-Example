package com.meneses.refactor;


import com.meneses.refactor.camera.model.CameraCommand;
import com.meneses.refactor.camera.model.CameraCommandResult;
import com.meneses.refactor.media.MediaPublisher;
import com.meneses.refactor.media.MediaSubscriber;

import java.sql.Array;

public class CameraService {
    private static CameraService instance;

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
}

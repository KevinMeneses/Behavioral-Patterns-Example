package com.meneses.legacy.camera.model;

public class CameraCommand {
    String token;
    int code;
    String information;
    String type;
    long fetchSize;
    long offset;

    public static class Builder {
        private final CameraCommand cameraCommand;

        public Builder() {
            this.cameraCommand = new CameraCommand();
        }

        public Builder setToken(String token) {
            cameraCommand.token = token;
            return this;
        }

        public Builder setCode(int code) {
            cameraCommand.code = code;
            return this;
        }

        public Builder setInformation(String information) {
            cameraCommand.information = information;
            return this;
        }

        public Builder setType(String type) {
            cameraCommand.type = type;
            return this;
        }

        public Builder setFetchSize(int fetchSize) {
            cameraCommand.fetchSize = fetchSize;
            return this;
        }

        public Builder setOffset(int offset) {
            cameraCommand.offset = offset;
            return this;
        }

        public CameraCommand build() {
            return cameraCommand;
        }
    }
}

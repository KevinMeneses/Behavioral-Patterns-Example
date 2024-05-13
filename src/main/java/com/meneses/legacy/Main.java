package com.meneses.legacy;

import com.meneses.legacy.camera.AudioRecorder;
import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.ImageRecorder;
import com.meneses.legacy.camera.VideoRecorder;
import com.meneses.legacy.logger.impl.CameraAnalytics;
import com.meneses.legacy.videoplayer.VideoPlayer;
import com.meneses.legacy.videoplayer.VideoPlayerView;
import com.meneses.legacy.camera.FullCamera;
import com.meneses.legacy.camera.model.CameraMedia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CameraService cameraService = CameraService.getInstance();
        cameraService.setToken("token");

        System.out.println(
                "Seleccione su camara:" +
                " 1. CamaraX" +
                        " 2. CamaraY" +
                        " 3. CamaraZ"
        );

        Scanner scanner = new Scanner(System.in);
        int cameraType = scanner.nextInt();

        CameraAnalytics cameraAnalytics = CameraAnalytics.create(cameraType);
        CameraFactory cameraFactory = new CameraFactory(cameraService, cameraAnalytics);
        Camera camera = cameraFactory.create(cameraType);

        if (camera == null) {
            System.out.println("Opcion no soportada");
            return;
        }

        System.out.println(
                "Que desea hacer:" +
                        " 1. Tomar foto" +
                        " 2. Iniciar grabación de video" +
                        " 3. Iniciar grabación de audio" +
                        " 4. Iniciar streaming de video"
        );

        int action = scanner.nextInt();
        Boolean result;

        try {
            switch (action) {
                case 1 -> {
                    result = ((ImageRecorder)camera).takePhoto();
                    System.out.println("Foto tomada: " + result);
                }
                case 2 -> {
                    result = ((VideoRecorder) camera).startVideoRecording();
                    System.out.println("Grabacion de video iniciada: " + result);
                    System.out.println("Ingrese cualquier tecla para finalizar la grabación");
                    scanner.nextByte();
                    result = ((VideoRecorder) camera).stopVideoRecording();
                    System.out.println("Grabacion de video terminada: " + result);
                }
                case 3 -> {
                    result = ((AudioRecorder) camera).startAudioRecording();
                    System.out.println("Grabacion de audio iniciada: " + result);
                    System.out.println("Ingrese cualquier tecla para finalizar la grabación");
                    scanner.nextByte();
                    result = ((AudioRecorder) camera).stopAudioRecording();
                    System.out.println("Grabacion de audio terminada: " + result);
                }
                case 4 -> {
                    System.out.println(
                            "Elije el modo de reproduccion:" +
                                    " 1. Seleccionar un video" +
                                    " 2. Lista de reproducción" +
                                    " 3. Video unificado"
                    );

                    action = scanner.nextInt();
                    VideoPlayer videoPlayer = new VideoPlayer((FullCamera) camera);
                    VideoPlayerView videoPlayerView = new VideoPlayerView(videoPlayer);
                    CameraMedia[] medias = ((FullCamera) camera).getAllMediaInfo();

                    switch (action) {
                        case 1 -> {
                            System.out.println("Hay " + medias.length + " videos, qué video que desea ver?");
                            int videoIndex = scanner.nextInt() - 1;
                            videoPlayer.setMedia(medias[videoIndex]);
                        }
                        case 2 -> videoPlayer.setMedias(medias, false);
                        case 3 -> videoPlayer.setMedias(medias, true);
                    }

                    while (true) {
                        System.out.println(
                                "Que desea realizar:" +
                                        " 1. Reproducir" +
                                        " 2. Pausar" +
                                        " 3. Detener" +
                                        " 4. Adelantar" +
                                        " 5. Regresar" +
                                        " 6. Bloquear"
                        );
                        action = scanner.nextInt();

                        switch (action) {
                            case 1,2 -> videoPlayerView.clickPlay();
                            case 3 -> videoPlayerView.clickStop();
                            case 4 -> videoPlayerView.clickNext();
                            case 5 -> videoPlayerView.clickPrevious();
                            case 6 -> videoPlayerView.clickLock();
                        }
                    }
                }
                default -> System.out.println("Opcion no soportada");
            }
        } catch (ClassCastException exception) {
            System.out.println("Opcion no soportada");
        }
    }
}

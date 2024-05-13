package com.meneses.refactor;

import com.meneses.refactor.camera.*;
import com.meneses.refactor.camera.impl.CameraZ;
import com.meneses.refactor.camera.model.CameraMedia;
import com.meneses.refactor.logger.impl.CameraAnalytics;
import com.meneses.refactor.videoplayer.VideoPlayer;
import com.meneses.refactor.videoplayer.VideoPlayerView;
import com.meneses.refactor.videoplayer.strategy.MultipleAsOneStrategy;
import com.meneses.refactor.videoplayer.strategy.PlaybackStrategy;
import com.meneses.refactor.videoplayer.strategy.PlaylistStrategy;
import com.meneses.refactor.videoplayer.strategy.SingleVideoStrategy;

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
                    result = ((VideoRecorder)camera).startVideoRecording();
                    System.out.println("Grabacion de video iniciada: " + result);
                    System.out.println("Ingrese cualquier tecla para finalizar la grabación");
                    scanner.nextByte();
                    result = ((VideoRecorder)camera).stopVideoRecording();
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
                    CameraMedia[] medias = ((FullCamera) camera).getAllMediaInfo();
                    VideoPlayer videoPlayer = new VideoPlayer();
                    PlaybackStrategy strategy;

                    switch (action) {
                        case 1 -> {
                            System.out.println("Hay " + medias.length + " videos, qué video que desea ver?");
                            int videoIndex = scanner.nextInt() - 1;
                            SingleVideoStrategy singleVideoStrategy = new SingleVideoStrategy(videoPlayer, (FullCamera)camera);
                            singleVideoStrategy.setMedia(medias[videoIndex]);
                            strategy = singleVideoStrategy;
                        }
                        case 2 -> {
                            PlaylistStrategy playlistStrategy = new PlaylistStrategy(videoPlayer, (FullCamera)camera);
                            playlistStrategy.setMedias(medias);
                            strategy = playlistStrategy;
                        }
                        case 3 -> {
                            MultipleAsOneStrategy multipleAsOneStrategy = new MultipleAsOneStrategy(videoPlayer, (FullCamera)camera);
                            multipleAsOneStrategy.setMedias(medias);
                            strategy = multipleAsOneStrategy;
                        }
                        default -> {
                            System.out.println("Opcion no soportada");
                            return;
                        }
                    }

                    VideoPlayerView videoPlayerView = new VideoPlayerView(strategy);

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
        } catch (Exception exception) {
            System.out.println("Opcion no soportada");
        }
    }
}

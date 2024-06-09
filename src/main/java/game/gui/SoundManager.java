package game.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundManager {
    private static MediaPlayer mediaPlayer;

    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            URL resource = SoundManager.class.getResource("/Soundtrack.mp3");
            if (resource == null) {
                System.err.println("Audio file not found.");
                return;
            }
            Media media = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the soundtrack
        }
        mediaPlayer.play();
    }

    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void pauseBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void resumeBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
}


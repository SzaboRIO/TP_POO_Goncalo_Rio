package Adventure;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The Audio class provides functionality for playing audio files within the Adventure game.
 * This utility class handles the loading and playback of sound effects and music.
 */
public class Audio {

    /**
     * Plays an audio file from the specified path.
     * This method attempts to load and play the audio file asynchronously.
     * If the file doesn't exist or an error occurs during playback, the method
     * fails silently to prevent interruption of the game flow.
     *
     * @param caminho The file path to the audio file to be played
     */
    public static void playMusic(String caminho) {
        try {
            File audio = new File(caminho);
            if (audio.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                // File not found - silent failure
            }
        } catch (Exception e) {
            System.out.println();
        }
    }
}
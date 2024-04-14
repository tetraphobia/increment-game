package com.wyvrn.increment;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Audio
 */
public class Audio {
    public static void playMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        URL musicFile = Audio.class.getResource("underclocked.mp3");

        AudioInputStream stream = AudioSystem.getAudioInputStream(musicFile);
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

package com.example.knightmove.Model;
import java.io.File;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
public class Sound {
    private final static String badSound = "src\\main\\java\\com\\example\\knightmove\\sounds\\error.wav";
    private final static String goodSound = "src\\main\\java\\com\\example\\knightmove\\sounds\\success.wav";
    private final static String toggleButton = "src\\main\\java\\com\\example\\knightmove\\sounds\\toggle.wav";
    private final static String programOn = "src\\main\\java\\com\\example\\knightmove\\sounds\\programOn.wav";
    private final static String login = "src\\main\\java\\com\\example\\knightmove\\sounds\\loginOK.wav";
    private final static String exit = "src\\main\\java\\com\\example\\knightmove\\sounds\\exit.wav";


    public void exitSound() throws Exception{
        File yourFile = new File(exit);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void loginSound() throws Exception{
        File yourFile = new File(login);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void programOnSound() throws Exception{
        File yourFile = new File(programOn);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void errorSound() throws Exception{
        File yourFile = new File(badSound);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void successSound() throws Exception{
        File yourFile = new File(goodSound);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void addSound() throws Exception{
        File yourFile = new File(toggleButton);
        AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }
    /******Sounds*****/


    public void badSound() {
        Sound s = new Sound();
        try {
            s.errorSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void goodSound() {
        Sound s = new Sound();
        try {
            s.successSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

}

package com.project;

import java.io.*;

public class HighScoreManager {
    private static HighScoreManager instance;
    private static final String HIGH_SCORE_FILE = "highscore.dat";
    private int highScore;

    private HighScoreManager() {
        this.highScore = loadHighScore();
    }

    public static synchronized HighScoreManager getInstance() {
        if (instance == null) {
            instance = new HighScoreManager();
        }
        return instance;
    }

    public void saveHighScore() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            dos.writeInt(this.highScore);
        } catch (IOException e) {
            System.out.println("Unable to save high score: " + e.getMessage());
        }
    }

    private int loadHighScore() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            return dis.readInt();
        } catch (IOException e) {
            System.out.println("Unable to load high score: " + e.getMessage());
            return 0;
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        if (highScore > this.highScore) {
            this.highScore = highScore;
            saveHighScore();  // Save new high score automatically
        }
    }
}


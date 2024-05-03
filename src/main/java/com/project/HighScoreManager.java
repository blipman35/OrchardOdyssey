package com.project;

import java.io.*;

public class HighScoreManager {

    private static final String HIGH_SCORE_FILE = "highscore.dat";
    private int highScore;

    public HighScoreManager() {
        this.highScore = loadHighScore();
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
        this.highScore = highScore;
        saveHighScore();
    }

}


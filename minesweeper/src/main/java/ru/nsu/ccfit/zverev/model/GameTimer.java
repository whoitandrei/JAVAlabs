package ru.nsu.ccfit.zverev.model;

public class GameTimer {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        }
        return elapsedTime;
    }

    public String getFormattedTime() {
        long totalMillis = getElapsedTime();
        long totalSeconds = totalMillis / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
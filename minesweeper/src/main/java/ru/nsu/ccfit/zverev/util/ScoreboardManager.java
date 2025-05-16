package ru.nsu.ccfit.zverev.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScoreboardManager {
    private static final String logfile = "logfile.txt";

    public ScoreboardManager() {
    }

    public void saveRecord (String nickname, int elapsedTime) {
        List<ScoreEntry> scores = loadHighScores();
        scores.add(new ScoreEntry(nickname, elapsedTime));
        scores.sort(Comparator.comparingInt(s -> s.score));

        if (scores.size() > 10) scores = scores.subList(0, 10);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logfile))) {
            for (ScoreEntry entry : scores) {
                writer.write(entry.nickname + "," + entry.score);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving high scores: " + e.getMessage());
        }
    }

    public static List<ScoreEntry> loadHighScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        File file = new File(logfile);

        if (!file.exists()) return scores;
        try (BufferedReader reader = new BufferedReader(new FileReader(logfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
            }
        } catch (IOException e) {
            System.err.println("Error loading high scores: " + e.getMessage());
        }
        return scores;
    }

    public record ScoreEntry(String nickname, int score) {};

}
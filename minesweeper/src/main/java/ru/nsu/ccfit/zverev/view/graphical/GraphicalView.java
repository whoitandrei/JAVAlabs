package ru.nsu.ccfit.zverev.view.graphical;

import ru.nsu.ccfit.zverev.controller.*;
import ru.nsu.ccfit.zverev.model.*;
import ru.nsu.ccfit.zverev.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class GraphicalView extends JFrame {
    private final GameController controller;
    private final JButton[][] buttons;
    private int height;
    private int width;
    private float difficulty;

    private final ImageIcon closedIcon = loadIcon("closed.png");
    private final ImageIcon openIcon = loadIcon("opened.png");
    private final ImageIcon flagIcon = loadIcon("flagged.png");
    private final ImageIcon mineIcon = loadIcon("mine.png");
    private final ImageIcon[] numberIcons = new ImageIcon[9];

    private final JLabel timerLabel;
    private Timer gameTimer;
    private int elapsedTime;

    public GraphicalView(GameController controller) {
        this.controller = controller;
        this.height = controller.getField().getHEIGHT();
        this.width = controller.getField().getWIDTH();
        this.difficulty = controller.getField().getDIFFICULTY();
        this.buttons = new JButton[height][width];

        loadNumberIcons();

        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        timerLabel = new JLabel("Time: 0s", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        add(timerLabel, gbc);

        JPanel boardPanel = createBoardPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        add(boardPanel, gbc);

        JPanel buttonPanel = getJPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);

        startTimer();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private JPanel getJPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> restartGame());
        buttonPanel.add(newGameButton);

        JButton changeSettingsButton = new JButton("Change Settings");
        changeSettingsButton.addActionListener(e -> {
            changeSettings();
            restartGame();
        });
        buttonPanel.add(changeSettingsButton);

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(e -> showHighScores());
        buttonPanel.add(highScoresButton);

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> showAbout());
        buttonPanel.add(aboutButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    private void changeSettings() {
        JDialog settingsDialog = new JDialog(this, "Settings", true);
        settingsDialog.setLayout(new GridLayout(3, 1, 5, 5));
        settingsDialog.setSize(300, 200);
        settingsDialog.setLocationRelativeTo(this);

        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField heightField = new JTextField(String.valueOf(controller.getField().getHEIGHT()), 10);
        heightPanel.add(new JLabel("height:"));
        heightPanel.add(heightField);
        settingsDialog.add(heightPanel);


        JPanel widthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField widthField = new JTextField(String.valueOf(controller.getField().getWIDTH()), 10);
        widthPanel.add(new JLabel("width:"));
        widthPanel.add(widthField);
        settingsDialog.add(widthPanel);



        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        difficultyPanel.add(new JLabel("difficulty:"));
        JComboBox<String> difficultyCombo = new JComboBox<>(new String[]{"EASY", "MEDIUM", "HARD"});
        difficultyCombo.setSelectedItem(toStringDifficulty(controller.getField().getDIFFICULTY()));
        difficultyPanel.add(difficultyCombo);
        bottomPanel.add(difficultyPanel, BorderLayout.WEST);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");


        saveButton.addActionListener(e -> {
            try {
                height = Integer.parseInt(heightField.getText());
                width = Integer.parseInt(widthField.getText());
                difficulty = toFloatDifficulty((String) Objects.requireNonNull(difficultyCombo.getSelectedItem()));
                settingsDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(settingsDialog, "incorrect input!", "error!", JOptionPane.ERROR_MESSAGE);
            }
            settingsDialog.dispose();
        });

        cancelButton.addActionListener(e -> settingsDialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        settingsDialog.add(bottomPanel);
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(this);
        settingsDialog.setVisible(true);
    }



    private JPanel createBoardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                JButton button = getJButton(row, col);

                buttons[row][col] = button;

                gbc.gridx = col;
                gbc.gridy = row;
                panel.add(button, gbc);
            }
        }
        return panel;
    }

    private JButton getJButton(int row, int col) {
        JButton button = new JButton();

        button.setPreferredSize(new Dimension(32, 32));
        button.setMinimumSize(new Dimension(32, 32));
        button.setMaximumSize(new Dimension(32, 32));
        button.setIcon(closedIcon);

        final int r = row, c = col;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) controller.openCell(c, r);
                else if (SwingUtilities.isRightMouseButton(e)) controller.changeFlag(c, r);
                updateBoard();
            }
        });
        return button;
    }

    private void startTimer() {
        elapsedTime = 0;
        gameTimer = new Timer(1000, e -> {
            elapsedTime++;
            timerLabel.setText("Time: " + elapsedTime + "s");
        });
        gameTimer.start();
    }

    private void stopTimer() { if (gameTimer != null) gameTimer.stop(); }

    private void updateBoard() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                CellState state = controller.getCellState(col, row);
                JButton button = buttons[row][col];

                switch (state) {
                    case OPENED:
                        int surroundingMines = controller.getMinesAround(col, row);
                        if (surroundingMines > 0) {
                            button.setIcon(numberIcons[surroundingMines]);
                        } else {
                            button.setIcon(openIcon);
                        }
                        break;
                    case FLAGGED:
                        button.setIcon(flagIcon);
                        break;
                    case CLOSED:
                        button.setIcon(closedIcon);
                        break;
                }
            }
        }

        if (controller.isGameOver()) {
            stopTimer();
            revealAllMines();
        }
    }

    private void revealAllMines() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (controller.getCellState(col, row) == CellState.IS_MINE) {
                    buttons[row][col].setIcon(mineIcon);
                }
            }
        }

        this.revalidate();
        this.repaint();
        Toolkit.getDefaultToolkit().sync();

        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}

            showGameOver();
        });
    }

    private void showGameOver() {
        stopTimer();
        if (controller.isGameWon()) {
            String playerName = JOptionPane.showInputDialog(this,
                    "Congratulations! You won in " + elapsedTime + " seconds!\nEnter your name for the leaderboard:");
            if (playerName != null && !playerName.trim().isEmpty()) controller.getScoreboardManager().saveRecord(playerName.trim(), elapsedTime);
        }

        int result = JOptionPane.showConfirmDialog(this,
                controller.isGameWon() ? "Play again?" : "Game Over! Play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) restartGame();
        else dispose();
    }

    private void restartGame() {
        dispose();
        new GraphicalView(new GameController(height, width, difficulty));
    }

    private void showHighScores() {
        List<ScoreboardManager.ScoreEntry> scores = ScoreboardManager.loadHighScores();

        if (scores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No high scores yet!", "High Scores", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder scoreText = new StringBuilder("<html><h2>High Scores</h2><br>");
        int rank = 1;
        for (ScoreboardManager.ScoreEntry entry : scores) {
            scoreText.append(rank++).append(". ").append(entry.nickname()).append(" - ")
                    .append(entry.score()).append(" sec.<br>");
        }
        scoreText.append("</html>");
        JOptionPane.showMessageDialog(this, scoreText.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAbout() {
        String aboutText = """
        <html>
        <h2>Minesweeper</h2>
        <p><b>Objective:</b> Uncover all safe cells without triggering a mine.</p>
        <p><b>Controls:</b></p>
        <ul>
            <li><b>Left Click</b> - Open a cell</li>
            <li><b>Right Click</b> - Place/Remove a flag</li>
        </ul>
        <p><b>Rules:</b></p>
        <ul>
            <li>The number in a cell shows how many mines are in the surrounding 8 cells.</li>
            <li>If you click on a mine, the game is over.</li>
            <li>If an empty cell is clicked, all adjacent empty cells will open automatically.</li>
            <li>To win, all non-mine cells must be revealed.</li>
        </ul>
        <p><i>Good luck and have fun!</i></p>
        </html>
        """;
        JOptionPane.showMessageDialog(this, aboutText, "About Minesweeper", JOptionPane.INFORMATION_MESSAGE);
    }

    private ImageIcon loadIcon(String fileName) {
        return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icons/" + fileName)));
    }

    private void loadNumberIcons() {
        for (int i = 1; i <= 8; i++) numberIcons[i] = loadIcon(i + ".png");
    }

    private float toFloatDifficulty(String d) {
        return switch (d) {
            case "EASY" -> 0.10F;
            case "HARD" -> 0.20F;
            default -> 0.15F;
        };
    }

    private String toStringDifficulty(float d) {
        if (d == 0.10F) return "EASY";
        else if (d == 0.15F) return "MEDIUM";
        return "HARD";
    }
}
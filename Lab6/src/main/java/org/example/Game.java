package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Game extends JFrame {
    private JTextField dotCountField;
    private JComboBox<String> player1Type;
    private JComboBox<String> player2Type;
    private JComboBox<String> difficultyLevel;
    private DrawingPanel canvasPanel;

    public String getPlayer1Type() {
        return (String) player1Type.getSelectedItem();
    }

    public String getPlayer2Type() {
        return (String) player2Type.getSelectedItem();
    }

    public String getDifficultyLevel() {
        return (String) difficultyLevel.getSelectedItem();
    }

    public Game() {
        setTitle("Puncte ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Config panel (sus)
        JPanel configPanel = new JPanel();
        player1Type = new JComboBox<>(new String[]{"Human", "AI"});
        player2Type = new JComboBox<>(new String[]{"Human", "AI"});
        difficultyLevel = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});

        configPanel.add(new JLabel("P1:"));
        configPanel.add(player1Type);
        configPanel.add(new JLabel("P2:"));
        configPanel.add(player2Type);
        configPanel.add(new JLabel("Difficulty:"));
        configPanel.add(difficultyLevel);
        dotCountField = new JTextField("5", 5);
        JButton newGameButton = new JButton("New Game");
        configPanel.add(new JLabel("Numar de puncte:"));
        configPanel.add(dotCountField);
        configPanel.add(newGameButton);
        add(configPanel, BorderLayout.NORTH);

        // Canvas panel (centru)
        canvasPanel = new DrawingPanel(this); // trimitem referinta catre Game
        canvasPanel.setBackground(Color.WHITE);
        add(canvasPanel, BorderLayout.CENTER);

        // Control panel (jos)
        JPanel controlPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton scoreButton = new JButton("Score");
        JButton exportButton = new JButton("Export PNG");
        JButton exitButton = new JButton("Exit");

        controlPanel.add(loadButton);
        controlPanel.add(saveButton);
        controlPanel.add(scoreButton);
        controlPanel.add(exportButton);
        controlPanel.add(exitButton);
        add(controlPanel, BorderLayout.SOUTH);

        // New Game button
        newGameButton.addActionListener(e -> {
            try {
                int count = Integer.parseInt(dotCountField.getText());
                canvasPanel.generateDots(count);

                // Mutare AI dacă e rândul lui
                boolean isCurrentAI = (canvasPanel.isPlayerOneTurn() && getPlayer1Type().equals("AI")) ||
                        (!canvasPanel.isPlayerOneTurn() && getPlayer2Type().equals("AI"));
                if (isCurrentAI) {
                    canvasPanel.performAIMove(getDifficultyLevel());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number");
            }
        });

        // Score button
        scoreButton.addActionListener(e -> {
            double redScore = canvasPanel.getScoreForPlayer(Color.RED);
            double blueScore = canvasPanel.getScoreForPlayer(Color.BLUE);
            double minScore = canvasPanel.getMinimumPossibleScore();

            JOptionPane.showMessageDialog(this,
                    "Red score: " + String.format("%.2f", redScore) + "\n" +
                            "Blue score: " + String.format("%.2f", blueScore) + "\n" +
                            "Minimum possible score: " + String.format("%.2f", minScore));
        });

        // Export PNG button
        exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Image");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String fileName = fileToSave.getAbsolutePath();
                if (!fileName.toLowerCase().endsWith(".png")) {
                    fileName += ".png";
                }
                canvasPanel.exportAsImage(fileName);
                JOptionPane.showMessageDialog(this, "Image saved: " + fileName);
            }
        });

        // Save game button
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Game");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String fileName = fileToSave.getAbsolutePath();
                if (!fileName.toLowerCase().endsWith(".ser")) {
                    fileName += ".ser";
                }
                canvasPanel.saveToFile(fileName);
                JOptionPane.showMessageDialog(this, "Game saved: " + fileName);
            }
        });

        // Load game button
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Game");
            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                canvasPanel.loadFromFile(fileToLoad.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Game loaded: " + fileToLoad.getName());
            }
        });

        // Exit button
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}

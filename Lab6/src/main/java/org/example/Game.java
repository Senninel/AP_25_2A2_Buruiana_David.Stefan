package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class Game extends JFrame {
    private JTextField dotCountField;
    private DrawingPanel canvasPanel;

    public Game() {
        setTitle("Puncte ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel configPanel = new JPanel();
        dotCountField = new JTextField("5", 5);
        JButton newGameButton = new JButton("New Game");
        configPanel.add(new JLabel("Numar de puncte:"));
        configPanel.add(dotCountField);
        configPanel.add(newGameButton);
        add(configPanel, BorderLayout.NORTH);

        canvasPanel = new DrawingPanel();
        canvasPanel.setBackground(Color.WHITE);
        add(canvasPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton("Load"));
        controlPanel.add(new JButton("Save"));
        controlPanel.add(new JButton("Exit"));
        add(controlPanel, BorderLayout.SOUTH);

        newGameButton.addActionListener(e -> {
            try {
                int count = Integer.parseInt(dotCountField.getText());
                canvasPanel.generateDots(count);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number");
            }
        });

        setVisible(true);
    }
}

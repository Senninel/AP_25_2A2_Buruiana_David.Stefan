package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


class DrawingPanel extends JPanel {
    private final ArrayList<Point> dots = new ArrayList<>();
    private final Random random = new Random();

    public void generateDots(int count) {
        dots.clear();
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < count; i++) {
            int x = 50 + random.nextInt(width - 100);
            int y = 50 + random.nextInt(height - 100);
            dots.add(new Point(x, y));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : dots) {
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
    }
}

package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

class DrawingPanel extends JPanel {
    private final ArrayList<Point> dots = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private final Random random = new Random();
    private Point selectedPoint = null;
    private boolean playerOneTurn = true;
    private final Game game;

    public DrawingPanel(Game game) {
        this.game = game;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Point p : dots) {
                    if (p.distance(e.getPoint()) <= 10) {
                        if (selectedPoint == null) {
                            selectedPoint = p;
                        } else {
                            if (!selectedPoint.equals(p)) {
                                boolean alreadyExists = lines.stream().anyMatch(line ->
                                        (line.p1.equals(selectedPoint) && line.p2.equals(p)) ||
                                                (line.p1.equals(p) && line.p2.equals(selectedPoint))
                                );

                                if (!alreadyExists) {
                                    Color color = playerOneTurn ? Color.RED : Color.BLUE;
                                    lines.add(new Line(selectedPoint, p, color));
                                    playerOneTurn = !playerOneTurn;
                                    repaint();

                                    // Mutare AI dacă e rândul lui
                                    boolean isCurrentAI = (playerOneTurn && game.getPlayer1Type().equals("AI")) ||
                                            (!playerOneTurn && game.getPlayer2Type().equals("AI"));
                                    if (isCurrentAI) {
                                        String level = game.getDifficultyLevel();
                                        performAIMove(level);
                                    }
                                }
                                selectedPoint = null;
                            }
                        }
                        break;
                    }
                }
            }
        });
    }

    private static class Line implements Serializable {
        Point p1, p2;
        Color color;

        Line(Point p1, Point p2, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
        }
    }

    private List<Edge> generateAllEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                edges.add(new Edge(dots.get(i), dots.get(j)));
            }
        }
        return edges;
    }

    private Map<Point, Point> initializeDisjointSet() {
        Map<Point, Point> parent = new HashMap<>();
        for (Point p : dots) parent.put(p, p);
        return parent;
    }

    private Point find(Point p, Map<Point, Point> parent) {
        if (!parent.get(p).equals(p)) {
            parent.put(p, find(parent.get(p), parent));
        }
        return parent.get(p);
    }

    private void union(Point a, Point b, Map<Point, Point> parent) {
        parent.put(find(a, parent), find(b, parent));
    }

    public void performAIMove(String difficulty) {
        List<Edge> allEdges = generateAllEdges();

        if (difficulty.equals("Easy")) {
            allEdges.sort(Comparator.comparingDouble((Edge e) -> e.distance).reversed());
        } else if (difficulty.equals("Medium")) {
            Collections.shuffle(allEdges);
        } else {
            allEdges.sort(Comparator.comparingDouble(e -> e.distance)); // Hard (default)
        }

        Map<Point, Point> parent = initializeDisjointSet();
        List<Edge> tree = new ArrayList<>();

        for (Edge e : allEdges) {
            Point root1 = find(e.p1, parent);
            Point root2 = find(e.p2, parent);
            if (!root1.equals(root2)) {
                union(root1, root2, parent);
                tree.add(e);
                if (tree.size() == dots.size() - 1) break;
            }
        }

        for (Edge e : tree) {
            boolean alreadyDrawn = lines.stream().anyMatch(line ->
                    (line.p1.equals(e.p1) && line.p2.equals(e.p2)) ||
                            (line.p1.equals(e.p2) && line.p2.equals(e.p1))
            );

            if (!alreadyDrawn) {
                Color color = playerOneTurn ? Color.RED : Color.BLUE;
                lines.add(new Line(e.p1, e.p2, color));
                playerOneTurn = !playerOneTurn;
                repaint();

                boolean isCurrentAI = (playerOneTurn && game.getPlayer1Type().equals("AI")) ||
                        (!playerOneTurn && game.getPlayer2Type().equals("AI"));
                if (isCurrentAI) {
                    performAIMove(difficulty);
                }

                break;
            }
        }
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public double getMinimumPossibleScore() {
        List<Edge> edges = generateAllEdges();
        edges.sort(Comparator.comparingDouble(e -> e.distance));
        Map<Point, Point> parent = initializeDisjointSet();

        double total = 0;
        int added = 0;

        for (Edge e : edges) {
            Point root1 = find(e.p1, parent);
            Point root2 = find(e.p2, parent);
            if (!root1.equals(root2)) {
                union(root1, root2, parent);
                total += e.distance;
                added++;
                if (added == dots.size() - 1) break;
            }
        }

        return total;
    }

    public double getScoreForPlayer(Color playerColor) {
        return lines.stream()
                .filter(line -> line.color.equals(playerColor))
                .mapToDouble(line -> line.p1.distance(line.p2))
                .sum();
    }

    public void generateDots(int count) {
        dots.clear();
        lines.clear();
        selectedPoint = null;
        playerOneTurn = true;
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

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }

        g.setColor(Color.BLACK);
        for (Point p : dots) {
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
    }

    public void exportAsImage(String fileName) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        g2.dispose();

        try {
            File output = new File(fileName);
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(new ArrayList<>(dots));
            out.writeObject(new ArrayList<>(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            dots.clear();
            lines.clear();
            dots.addAll((ArrayList<Point>) in.readObject());
            lines.addAll((ArrayList<Line>) in.readObject());
            repaint();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

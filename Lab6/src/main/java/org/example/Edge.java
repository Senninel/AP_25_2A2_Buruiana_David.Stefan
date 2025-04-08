package org.example;

import java.awt.*;

public class Edge {
    Point p1, p2;
    double distance;

    Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = p1.distance(p2);
    }
}

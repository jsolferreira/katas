package kyu3;

import java.awt.*;
import java.util.List;
import java.util.*;

public class FindTheCheapestPath {

    public record AdjacentPoint(Point p, String direction, int cost) {
    }

    public record Distance(int cost, List<String> path) {
    }

    public static List<String> cheapestPath(int[][] t, Point start, Point finish) {

        final HashSet<Point> visitedPoints = new HashSet<>();
        final HashMap<Point, Distance> shortestDistance = new HashMap<>();
        shortestDistance.put(start, new Distance(0, new ArrayList<>()));
        final PriorityQueue<Point> unvisitedPoints = new PriorityQueue<>(Comparator
                .comparingInt(point -> shortestDistance.get(point).cost));

        Point p = start;
        while (!p.equals(finish)) {

            p = unvisitedPoints.stream().findFirst().orElse(start);
            Distance distance = shortestDistance.get(p);

            ArrayList<AdjacentPoint> adjacentPoints2 = getAdjacentPoints(t, p);

            for (AdjacentPoint adjacentPoint : adjacentPoints2) {
                if (!visitedPoints.contains(adjacentPoint.p)) {
                    int nextCost = adjacentPoint.cost + distance.cost;
                    ArrayList<String> nextPath = new ArrayList<>(distance.path);
                    nextPath.add(adjacentPoint.direction);

                    Distance nextDistance = new Distance(nextCost, nextPath);
                    shortestDistance.merge(adjacentPoint.p, nextDistance, (v1, v2) -> {
                        if (v1.cost > v2.cost) return v2;
                        return v1;
                    });

                    if (!unvisitedPoints.contains(adjacentPoint.p)) {
                        unvisitedPoints.add(adjacentPoint.p);
                    }
                }
            }

            visitedPoints.add(p);
            unvisitedPoints.remove(p);
        }

        return shortestDistance.get(finish).path;
    }

    private static ArrayList<AdjacentPoint> getAdjacentPoints(int[][] t, Point p) {

        final ArrayList<AdjacentPoint> adjacentPoints = new ArrayList<>();

        if (p.x > 0) {
            adjacentPoints.add(new AdjacentPoint(new Point(p.x - 1, p.y), "up", t[p.x - 1][p.y]));
        }

        if (p.x < t.length - 1) {
            adjacentPoints.add(new AdjacentPoint(new Point(p.x + 1, p.y), "down", t[p.x + 1][p.y]));
        }

        if (p.y > 0) {
            adjacentPoints.add(new AdjacentPoint(new Point(p.x, p.y - 1), "left", t[p.x][p.y - 1]));
        }

        if (p.y < t[p.x].length - 1) {
            adjacentPoints.add(new AdjacentPoint(new Point(p.x, p.y + 1), "right", t[p.x][p.y + 1]));
        }

        return adjacentPoints;
    }
}

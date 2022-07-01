package kyu3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class IceMaze {

    private record Path(int cost, List<Character> directions) {
    }

    private record Position(int i, int j, boolean end) {
        public Position(int i, int j) {
            this(i, j, false);
        }
    }

    public static List<Character> solve(String map) {
        final Character[][] characters = parseMap(map);
        final HashMap<Position, Path> pathMap = new HashMap<>();
        final HashSet<Position> visitedPositions = new HashSet<>();

        final Position start = getStart(characters);
        pathMap.put(start, new Path(0, new ArrayList<>()));

        walk(characters, start, visitedPositions, pathMap);

        return pathMap.entrySet().stream()
                .filter(entry -> entry.getKey().end)
                .map(entry -> entry.getValue().directions)
                .findFirst()
                .orElse(null);
    }

    private static void walk(Character[][] characters,
                             Position position,
                             HashSet<Position> visitedPositions,
                             HashMap<Position, Path> pathMap) {

        if (position.end) return;
        visitedPositions.add(position);

        List<Map.Entry<Position, Character>> unvisitedPositions = new ArrayList<>();

        Map.Entry<Position, Character> unvisitedPosition = Map.entry(position, ' ');

        unvisitedPositions.add(unvisitedPosition);

        while (unvisitedPosition != null) {

            visitedPositions.add(unvisitedPosition.getKey());

            List<Map.Entry<Position, Character>> nextPositions = nextPositions(characters, unvisitedPosition.getKey()).stream()
                    .filter(p -> !visitedPositions.contains(p.getKey()))
                    .toList();

            for (Map.Entry<Position, Character> positionCharacterEntry : nextPositions) {

                Position nexPosition = positionCharacterEntry.getKey();

                int positionCost = pathMap.get(unvisitedPosition.getKey()).cost;
                int newCost = positionCost + calculateCost(unvisitedPosition.getKey(), nexPosition);

                List<Character> positionDirections = pathMap.get(unvisitedPosition.getKey()).directions;
                ArrayList<Character> newDirections = new ArrayList<>(positionDirections);
                newDirections.add(positionCharacterEntry.getValue());

                if (!pathMap.containsKey(nexPosition)) {

                    pathMap.put(nexPosition, new Path(newCost, newDirections));
                } else {

                    int oldCost = pathMap.get(nexPosition).cost;
                    List<Character> oldDirections = pathMap.get(nexPosition).directions;

                    if (oldDirections.size() >= newDirections.size()) {

                        if (oldDirections.size() == newDirections.size()) {

                            if (oldCost > newCost) {
                                pathMap.put(nexPosition, new Path(newCost, newDirections));
                            } else {
                                pathMap.put(nexPosition, new Path(oldCost, oldDirections));
                            }
                        } else {

                            pathMap.put(nexPosition, new Path(newCost, newDirections));
                        }
                    }
                }
            }

            unvisitedPositions.addAll(nextPositions);

            Optional<Map.Entry<Position, Character>> min = unvisitedPositions.stream()
                    .filter(x -> pathMap.containsKey(x.getKey()))
                    .min(Comparator.comparingInt(o -> pathMap.get(o.getKey()).directions.size()));

            if (min.isPresent()) {

                unvisitedPosition = min.get();
                unvisitedPositions.remove(unvisitedPosition);
            } else if (!unvisitedPositions.isEmpty()) {

                unvisitedPosition = unvisitedPositions.remove(0);
            } else {

                unvisitedPosition = null;
            }
        }
    }

    private static Character[][] parseMap(String map) {

        return Arrays.stream(map.split("\n"))
                .map(line -> line.chars().mapToObj(c -> (char) c).toArray(Character[]::new))
                .toArray(Character[][]::new);
    }

    private static Position getStart(Character[][] map) {

        return IntStream.range(0, map.length)
                .mapToObj(i -> IntStream.range(0, map[i].length)
                        .filter(j -> map[i][j] == 'S')
                        .mapToObj(j -> new Position(i, j)))
                .flatMap(Function.identity())
                .findFirst()
                .orElse(null);
    }

    private static List<Map.Entry<Position, Character>> nextPositions(Character[][] map, Position position) {
        return List.of(
                Map.entry(goUp(map, position), 'u'),
                Map.entry(goDown(map, position), 'd'),
                Map.entry(goLeft(map, position), 'l'),
                Map.entry(goRight(map, position), 'r')
        );
    }

    private static Position goUp(Character[][] map, Position position) {

        return IntStream
                .range(0, position.i)
                .map(i -> position.i - i - 1)
                .filter(i -> map[i][position.j] == 'E' || map[i][position.j] == '#' || (i != position.i && map[i][position.j] == 'x'))
                .findFirst().stream()
                .mapToObj(i -> map[i][position.j] == '#' ? new Position(i + 1, position.j) : new Position(i, position.j, map[i][position.j] == 'E'))
                .findFirst()
                .orElseGet(() -> new Position(0, position.j));
    }

    private static Position goDown(Character[][] map, Position position) {

        return IntStream
                .range(position.i, map.length)
                .filter(i -> map[i][position.j] == 'E' || map[i][position.j] == '#' || (i != position.i && map[i][position.j] == 'x'))
                .findFirst().stream()
                .mapToObj(i -> map[i][position.j] == '#' ? new Position(i - 1, position.j) : new Position(i, position.j, map[i][position.j] == 'E'))
                .findFirst()
                .orElseGet(() -> new Position(map.length - 1, position.j));
    }

    private static Position goLeft(Character[][] map, Position position) {

        return IntStream
                .range(0, position.j)
                .map(j -> position.j - j - 1)
                .filter(j -> map[position.i][j] == 'E' || map[position.i][j] == '#' || (j != position.j && map[position.i][j] == 'x'))
                .findFirst().stream()
                .mapToObj(j -> map[position.i][j] == '#' ? new Position(position.i, j + 1) : new Position(position.i, j, map[position.i][j] == 'E'))
                .findFirst()
                .orElseGet(() -> new Position(position.i, 0));
    }

    private static Position goRight(Character[][] map, Position position) {

        return IntStream
                .range(position.j, map[position.i].length)
                .filter(j -> map[position.i][j] == 'E' || map[position.i][j] == '#' || (j != position.j && map[position.i][j] == 'x'))
                .findFirst().stream()
                .mapToObj(j -> map[position.i][j] == '#' ? new Position(position.i, j - 1) : new Position(position.i, j, map[position.i][j] == 'E'))
                .findFirst()
                .orElseGet(() -> new Position(position.i, map[position.i].length - 1));
    }

    private static int calculateCost(Position position1, Position position2) {

        return Math.abs((position1.i - position2.i) + (position1.j - position2.j));
    }
}

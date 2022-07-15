package kyu5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RoboScript2ImplementTheRS1Specification {

    private static final Pattern pattern = Pattern.compile("[LFR]\\d*");

    private enum Instruction {
        FORWARD('F'),
        ROTATE_LEFT('L'),
        ROTATE_RIGHT('R');

        private final char value;

        Instruction(char value) {
            this.value = value;
        }

        public static Instruction findByValue(Character c) {
            return Arrays.stream(values())
                    .filter(v -> v.value == c)
                    .findFirst()
                    .orElse(null);
        }
    }

    private record Command(Instruction instruction, int times) {
    }

    private record Position(int i, int j) {
    }

    private enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN;
    }

    public static String execute(String code) {

        final Matcher matcher = pattern.matcher(code);

        Direction direction = Direction.RIGHT;
        Position p = new Position(0, 0);

        ArrayList<Position> positions = new ArrayList<>();
        int minI = 0;
        int minJ = 0;
        int maxI = 0;
        int maxJ = 0;
        positions.add(p);

        while (matcher.find()) {
            final Command command = parseCommand(matcher.group());

            if (command.instruction.equals(Instruction.FORWARD)) {
                for (int i = 0; i < command.times; i++) {
                    p = moveForward(p, direction);
                    positions.add(p);
                    if (p.i < minI) minI = p.i;
                    if (p.i > maxI) maxI = p.i;
                    if (p.j < minJ) minJ = p.j;
                    if (p.j > maxJ) maxJ = p.j;
                }
            } else if (command.instruction.equals(Instruction.ROTATE_LEFT)) {
                for (int i = 0; i < command.times; i++) {
                    direction = rotateLeft(direction);
                }
            } else if (command.instruction.equals(Instruction.ROTATE_RIGHT)) {
                for (int i = 0; i < command.times; i++) {
                    direction = rotateRight(direction);
                }
            }
        }

        Character[][] board = new Character[maxI - minI + 1][maxJ - minJ + 1];

        for (Position position : positions) {
            board[position.i + Math.abs(minI)][position.j + Math.abs(minJ)] = '*';
        }

        return Arrays.stream(board).map(row -> Arrays.stream(row)
                        .map(c -> c == null ? " " : "*")
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\r\n"));
    }

    private static Command parseCommand(String command) {

        Instruction instruction = Instruction.findByValue(command.charAt(0));
        int times = command.substring(1).equals("") ? 1 : Integer.parseInt(command.substring(1));

        return new Command(instruction, times);
    }

    private static Position moveForward(Position position, Direction direction) {

        if (direction.equals(Direction.LEFT)) {
            return new Position(position.i, position.j - 1);
        } else if (direction.equals(Direction.RIGHT)) {
            return new Position(position.i, position.j + 1);
        } else if (direction.equals(Direction.UP)) {
            return new Position(position.i - 1, position.j);
        } else {
            return new Position(position.i + 1, position.j);
        }
    }

    private static Direction rotateRight(Direction direction) {

        if (direction.equals(Direction.LEFT)) {
            return Direction.UP;
        } else if (direction.equals(Direction.RIGHT)) {
            return Direction.DOWN;
        } else if (direction.equals(Direction.UP)) {
            return Direction.RIGHT;
        } else {
            return Direction.LEFT;
        }
    }

    private static Direction rotateLeft(Direction direction) {

        if (direction.equals(Direction.LEFT)) {
            return Direction.DOWN;
        } else if (direction.equals(Direction.RIGHT)) {
            return Direction.UP;
        } else if (direction.equals(Direction.UP)) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }
}

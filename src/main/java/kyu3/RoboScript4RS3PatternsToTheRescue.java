package kyu3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RoboScript4RS3PatternsToTheRescue {

    final static Pattern patternBlockWithoutPatternCallsRegex = Pattern.compile("p(\\d+)([^P]*?)q");
    final static Pattern patternBlock = Pattern.compile("p(\\d+).*?q");

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

    private record Command(List<Command> commands, Instruction instruction, int times) {
    }

    private record Position(int i, int j) {
    }

    private static class Cursor {

        int i = 0;
        int j = 0;
        Direction direction = Direction.RIGHT;

        public Cursor() {
        }

        public Position getPosition() {
            return new Position(i, j);
        }

        public void moveForward() {

            if (direction.equals(Direction.LEFT)) {
                j = j - 1;
            } else if (direction.equals(Direction.RIGHT)) {
                j = j + 1;
            } else if (direction.equals(Direction.UP)) {
                i = i - 1;
            } else {
                i = i + 1;
            }
        }

        public void rotateRight() {

            if (direction.equals(Direction.LEFT)) {
                direction = Direction.UP;
            } else if (direction.equals(Direction.RIGHT)) {
                direction = Direction.DOWN;
            } else if (direction.equals(Direction.UP)) {
                direction = Direction.RIGHT;
            } else {
                direction = Direction.LEFT;
            }
        }

        public void rotateLeft() {

            if (direction.equals(Direction.LEFT)) {
                direction = Direction.DOWN;
            } else if (direction.equals(Direction.RIGHT)) {
                direction = Direction.UP;
            } else if (direction.equals(Direction.UP)) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }
        }
    }

    private enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public static String execute(String code) {

        code = unfoldPatterns(code);

        final Cursor cursor = new Cursor();

        final ArrayList<Position> positions = new ArrayList<>();
        positions.add(cursor.getPosition());
        int minI = 0;
        int minJ = 0;
        int maxI = 0;
        int maxJ = 0;

        final ArrayList<Command> commands = parseCommands(code);

        getPositions(commands, positions, cursor);

        for (Position position : positions) {
            if (position.i < minI) minI = position.i;
            if (position.i > maxI) maxI = position.i;
            if (position.j < minJ) minJ = position.j;
            if (position.j > maxJ) maxJ = position.j;
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

    private static String unfoldPatterns(String code) {

        final HashSet<String> patterns = new HashSet<>();

        boolean found = true;

        while (found) {
            final Matcher patternBlockMatcher = patternBlockWithoutPatternCallsRegex.matcher(code);

            code = patternBlockWithoutPatternCallsRegex.matcher(code).replaceAll("");

            found = false;

            while (patternBlockMatcher.find()) {
                found = true;

                final String patternName = patternBlockMatcher.group(1);

                if (patterns.contains(patternName)) {
                    throw new RuntimeException();
                }

                patterns.add(patternName);

                final String pattern = patternBlockMatcher.group(2) + "$1";

                code = code.replaceAll("P" + patternName + "((?=\\D|$))", pattern);
            }
        }

        final Matcher patternBlockMatcher = patternBlock.matcher(code);

        while (patternBlockMatcher.find()) {
            final String patternName = patternBlockMatcher.group(1);

            if (patterns.contains(patternName)) {
                throw new RuntimeException();
            } else {
                code = code.replaceAll("p" + patternName + ".*?q", "");
            }
        }

        if (code.contains("P")) {
            throw new RuntimeException();
        }

        return code;
    }

    private static ArrayList<Command> parseCommands(String code) {

        ArrayList<Command> commands = new ArrayList<>();

        parseCommandsAux(code, commands, 0);

        return commands;
    }

    private static int parseCommandsAux(String code, ArrayList<Command> commands, int startIndex) {

        Character previousChar = null;
        String previousNumber = null;
        ArrayList<Command> previousCommands = new ArrayList<>();

        for (int i = startIndex; i < code.length(); i++) {
            char c = code.charAt(i);

            if (c == 'F' || c == 'R' || c == 'L') {
                addToCommands(commands, previousCommands, previousChar, previousNumber);

                previousChar = c;
                previousNumber = null;
                previousCommands = new ArrayList<>();
            } else if (Character.isDigit(c)) {
                previousNumber = previousNumber == null ? String.valueOf(c) : previousNumber + c;
            } else if (c == '(') {
                addToCommands(commands, previousCommands, previousChar, previousNumber);

                previousChar = null;
                previousNumber = null;
                previousCommands = new ArrayList<>();

                i = parseCommandsAux(code, previousCommands, i + 1);
            } else if (c == ')') {
                addToCommands(commands, previousCommands, previousChar, previousNumber);
                return i;
            }
        }

        addToCommands(commands, previousCommands, previousChar, previousNumber);

        return code.length();
    }

    private static void addToCommands(List<Command> commands, List<Command> previousCommands, Character character, String times) {

        int number = times == null ? 1 : Integer.parseInt(times);
        if (character != null) {
            commands.add(new Command(Collections.emptyList(), Instruction.findByValue(character), number));
        } else if (!previousCommands.isEmpty()) {
            commands.add(new Command(previousCommands, null, number));
        }
    }

    private static void getPositions(List<Command> commands, List<Position> positions, Cursor cursor) {

        for (Command command : commands) {
            if (!command.commands.isEmpty()) {
                for (int i = 0; i < command.times; i++) {
                    getPositions(command.commands, positions, cursor);
                }
            } else if (command.instruction.equals(Instruction.FORWARD)) {
                for (int i = 0; i < command.times; i++) {
                    cursor.moveForward();
                    positions.add(cursor.getPosition());
                }
            } else if (command.instruction.equals(Instruction.ROTATE_LEFT)) {
                for (int i = 0; i < command.times; i++) {
                    cursor.rotateLeft();
                }
            } else if (command.instruction.equals(Instruction.ROTATE_RIGHT)) {
                for (int i = 0; i < command.times; i++) {
                    cursor.rotateRight();
                }
            }
        }
    }
}

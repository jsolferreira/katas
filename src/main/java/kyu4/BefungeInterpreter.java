package kyu4;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.function.BiConsumer;

public class BefungeInterpreter {

    private final Stack<Integer> stack = new Stack<>();
    private char[][] instructions;
    private Direction direction = Direction.RIGHT;
    private boolean stringMode = false;
    private boolean skipInstruction = false;

    private final StringBuilder sb = new StringBuilder();

    BiConsumer<Character, Runnable> runInstruction = (val, runnable) -> {
        if (skipInstruction) {

            skipInstruction = false;
        } else {

            if (this.stringMode) {

                addToStack(val);
            } else {

                runnable.run();
            }
        }
    };

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        public static Direction random() {
            Random r = new Random();
            int low = 0;
            int high = 3;

            return Direction.values()[r.nextInt(high - low) + low];
        }
    }

    public String interpret(String code) {

        parseCode(code);

        int i = 0;
        int j = 0;

        while (instructions[i][j] != '@') {

            final char val = instructions[i][j];

            switch (val) {
                case '+' -> runInstruction.accept(val, this::sum);
                case '-' -> runInstruction.accept(val, this::difference);
                case '*' -> runInstruction.accept(val, this::multiply);
                case '/' -> runInstruction.accept(val, this::divide);
                case '%' -> runInstruction.accept(val, this::modulo);
                case '!' -> runInstruction.accept(val, this::negate);
                case '`' -> runInstruction.accept(val, this::greaterThan);
                case '>' -> runInstruction.accept(val, () -> move(Direction.RIGHT));
                case '<' -> runInstruction.accept(val, () -> move(Direction.LEFT));
                case 'v' -> runInstruction.accept(val, () -> move(Direction.DOWN));
                case '^' -> runInstruction.accept(val, () -> move(Direction.UP));
                case '?' -> runInstruction.accept(val, () -> move(Direction.random()));
                case '_' -> runInstruction.accept(val, () -> popAndMove(Direction.RIGHT, Direction.LEFT));
                case '|' -> runInstruction.accept(val, () -> popAndMove(Direction.DOWN, Direction.UP));
                case ':' -> runInstruction.accept(val, this::duplicateStackValue);
                case '\\' -> runInstruction.accept(val, this::swap);
                case '$' -> runInstruction.accept(val, this::popStack);
                case '.' -> runInstruction.accept(val, this::popAndWriteAsInteger);
                case ',' -> runInstruction.accept(val, this::popAndWriteAsciiValue);
                case '#' -> runInstruction.accept(val, this::skipInstruction);
                case 'p' -> runInstruction.accept(val, this::put);
                case 'g' -> runInstruction.accept(val, this::get);
                case ' ' -> runInstruction.accept(val, this::noOp);
                case '"' -> this.toggleStringMode();
                default -> runInstruction.accept(val, () -> addToStack(Character.getNumericValue(val)));
            }

            if (direction.equals(Direction.RIGHT)) {
                j++;
            } else if (direction.equals(Direction.LEFT)) {
                j--;
            } else if (direction.equals(Direction.DOWN)) {
                i++;
            } else {
                i--;
            }
        }

        return sb.toString();
    }

    private void parseCode(String code) {

        instructions = Arrays.stream(code.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private void addToStack(int val) {

        stack.add(val);
    }

    private void sum() {

        final int a = stack.pop();
        final int b = stack.pop();

        stack.add(a + b);
    }

    private void difference() {

        final int a = stack.pop();
        final int b = stack.pop();

        stack.add(b - a);
    }

    private void multiply() {

        final int a = stack.pop();
        final int b = stack.pop();

        stack.add(a * b);
    }

    private void divide() {

        final int a = stack.pop();
        final int b = stack.pop();

        stack.add(a == 0 ? 0 : b / a);
    }

    private void modulo() {

        final int a = stack.pop();
        final int b = stack.pop();

        stack.add(a == 0 ? 0 : b % a);
    }

    private void negate() {

        stack.add(stack.pop() == 0 ? 1 : 0);
    }

    private void greaterThan() {

        stack.add(stack.pop() < stack.pop() ? 1 : 0);
    }

    private void move(Direction direction) {

        this.direction = direction;
    }

    private void popAndMove(Direction directionIfZero, Direction directionIfNotZero) {

        if (stack.pop() == 0) {

            this.move(directionIfZero);
        } else {

            this.move(directionIfNotZero);
        }
    }

    private void toggleStringMode() {

        stringMode = !stringMode;
    }

    private void duplicateStackValue() {

        stack.add(stack.empty() ? 0 : stack.lastElement());
    }

    private void swap() {

        if (stack.size() == 1) {

            stack.add(0);
        } else {
            final int a = stack.pop();
            final int b = stack.pop();

            stack.add(a);
            stack.add(b);
        }
    }

    private void popStack() {

        stack.pop();
    }

    private void popAndWriteAsInteger() {

        sb.append(stack.pop());
    }

    private void popAndWriteAsciiValue() {

        sb.append((char) ((int) stack.pop()));
    }

    private void skipInstruction() {

        skipInstruction = true;
    }

    private void put() {

        final int x = stack.pop();
        final int y = stack.pop();
        final int v = stack.pop();

        instructions[x][y] = (char) v;
    }

    private void get() {

        final int x = stack.pop();
        final int y = stack.pop();

        stack.add((int) instructions[x][y]);
    }

    private void noOp() {
    }
}

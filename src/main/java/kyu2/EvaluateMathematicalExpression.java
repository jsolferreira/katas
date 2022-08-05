package kyu2;

import java.util.ArrayList;
import java.util.List;

public class EvaluateMathematicalExpression {

    private enum TokenType {
        LEFT_PAREN,
        RIGHT_PAREN,
        DOT,
        MINUS,
        PLUS,
        SLASH,
        STAR,
        NUMBER,
        EOF
    }

    private record Token(TokenType type, Double literal) {
    }

    private static class Scanner {

        private final String source;
        private final List<Token> tokens = new ArrayList<>();
        private int start = 0;
        private int current = 0;

        Scanner(String source) {
            this.source = source;
        }

        public List<Token> scanTokens() {
            while (!isAtEnd()) {
                start = current;
                scanToken();
            }
            tokens.add(new Token(TokenType.EOF, null));
            return tokens;
        }

        private boolean isAtEnd() {
            return current >= source.length();
        }

        private void scanToken() {
            char c = advance();
            switch (c) {
                case '(' -> addToken(TokenType.LEFT_PAREN);
                case ')' -> addToken(TokenType.RIGHT_PAREN);
                case '.' -> addToken(TokenType.DOT);
                case '-' -> addToken(TokenType.MINUS);
                case '+' -> addToken(TokenType.PLUS);
                case '*' -> addToken(TokenType.STAR);
                case '/' -> addToken(TokenType.SLASH);
                case ' ' -> {
                }
                default -> number();
            }
        }

        private char advance() {
            current++;
            return source.charAt(current - 1);
        }

        private void addToken(TokenType type) {
            addToken(type, null);
        }

        private void addToken(TokenType type, Double literal) {
            tokens.add(new Token(type, literal));
        }

        private char peek() {
            if (isAtEnd()) return '\0';
            return source.charAt(current);
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private void number() {
            while (isDigit(peek())) advance();
            if (peek() == '.' && isDigit(peekNext())) {
                advance();
                while (isDigit(peek())) advance();
            }
            addToken(TokenType.NUMBER,
                    Double.parseDouble(source.substring(start, current)));
        }

        private char peekNext() {
            if (current + 1 >= source.length()) return '\0';
            return source.charAt(current + 1);
        }
    }

    private static class Parser {

        private final List<Token> tokens;
        private int current = 0;

        Parser(List<Token> tokens) {
            this.tokens = tokens;
        }

        public Expr parse() {
            return expression();
        }

        private Expr expression() {
            return term();
        }

        private boolean match(TokenType... types) {
            for (TokenType type : types) {
                if (check(type)) {
                    advance();
                    return true;
                }
            }
            return false;
        }

        private boolean check(TokenType type) {
            if (isAtEnd()) return false;
            return peek().type == type;
        }

        private void advance() {
            if (!isAtEnd()) current++;
        }

        private boolean isAtEnd() {
            return peek().type == TokenType.EOF;
        }

        private Token peek() {
            return tokens.get(current);
        }

        private Token previous() {
            return tokens.get(current - 1);
        }

        private Expr term() {
            Expr expr = factor();
            while (match(TokenType.MINUS, TokenType.PLUS)) {
                Token operator = previous();
                Expr right = factor();
                expr = new Expr.Binary(expr, operator, right);
            }
            return expr;
        }

        private Expr factor() {
            Expr expr = unary();
            while (match(TokenType.SLASH, TokenType.STAR)) {
                Token operator = previous();
                Expr right = unary();
                expr = new Expr.Binary(expr, operator, right);
            }
            return expr;
        }

        private Expr unary() {
            if (match(TokenType.MINUS)) {
                Token operator = previous();
                Expr right = unary();
                return new Expr.Unary(operator, right);
            }
            return primary();
        }

        private Expr primary() {
            if (match(TokenType.NUMBER)) {
                return new Expr.Literal(previous().literal);
            }
            if (match(TokenType.LEFT_PAREN)) {
                Expr expr = expression();
                if (check(TokenType.RIGHT_PAREN)) advance();
                return new Expr.Grouping(expr);
            }
            return null;
        }
    }

    private static abstract class Expr {

        interface Visitor<R> {
            R visitBinaryExpr(Binary expr);

            R visitGroupingExpr(Grouping expr);

            R visitLiteralExpr(Literal expr);

            R visitUnaryExpr(Unary expr);
        }

        static class Binary extends Expr {
            Binary(Expr left, Token operator, Expr right) {
                this.left = left;
                this.operator = operator;
                this.right = right;
            }

            @Override
            <R> R accept(Visitor<R> visitor) {
                return visitor.visitBinaryExpr(this);
            }

            final Expr left;
            final Token operator;
            final Expr right;
        }

        static class Grouping extends Expr {
            Grouping(Expr expression) {
                this.expression = expression;
            }

            @Override
            <R> R accept(Visitor<R> visitor) {
                return visitor.visitGroupingExpr(this);
            }

            final Expr expression;
        }

        static class Literal extends Expr {
            Literal(Double value) {
                this.value = value;
            }

            @Override
            <R> R accept(Visitor<R> visitor) {
                return visitor.visitLiteralExpr(this);
            }

            final Double value;
        }

        static class Unary extends Expr {
            Unary(Token operator, Expr right) {
                this.operator = operator;
                this.right = right;
            }

            @Override
            <R> R accept(Visitor<R> visitor) {
                return visitor.visitUnaryExpr(this);
            }

            final Token operator;
            final Expr right;
        }

        abstract <R> R accept(Visitor<R> visitor);
    }

    private static class Interpreter implements Expr.Visitor<Double> {

        @Override
        public Double visitLiteralExpr(Expr.Literal expr) {
            return expr.value;
        }

        @Override
        public Double visitUnaryExpr(Expr.Unary expr) {
            Double right = evaluate(expr.right);

            if (expr.operator.type.equals(TokenType.MINUS)) {
                return -right;
            } else {
                return null;
            }
        }

        @Override
        public Double visitGroupingExpr(Expr.Grouping expr) {
            return evaluate(expr.expression);
        }

        @Override
        public Double visitBinaryExpr(Expr.Binary expr) {
            Double left = evaluate(expr.left);
            Double right = evaluate(expr.right);

            return switch (expr.operator.type) {
                case MINUS -> left - right;
                case SLASH -> left / right;
                case STAR -> left * right;
                case PLUS -> left + right;
                default -> null;
            };
        }

        private Double evaluate(Expr expr) {
            return expr.accept(this);
        }
    }

    public double calculate(String expression) {

        final Scanner scanner = new Scanner(expression);
        final List<Token> tokens = scanner.scanTokens();

        final Parser parser = new Parser(tokens);
        final Expr parse = parser.parse();

        final Interpreter interpreter = new Interpreter();
        return interpreter.evaluate(parse);
    }
}

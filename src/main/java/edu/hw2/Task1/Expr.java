package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    String toString();

    record Constant(long value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

        @Override
        public String toString() {
            return "(-" + expr.toString() + ")";
        }
    }

    record Exponent(Expr base, long power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), power);
        }

        @Override
        public String toString() {
            return "((" + base.toString() + ")^" + power + ")";
        }
    }

    record Addition(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }

        @Override
        public String toString() {
            return "(" + left.toString() + "+" + right.toString() + ")";
        }
    }

    record Multiplication(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }

        @Override
        public String toString() {
            return left.toString() + "*" + right.toString();
        }
    }
}

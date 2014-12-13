/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / (Associativity.LEFT), \/ __ |/ (Associativity.LEFT), _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

public enum Operators {

    EQUAL(Associativity.LEFT, "="),
    LESSTHAN(Associativity.LEFT, "<"),
    LESSTHAN_EQUAL(Associativity.LEFT, "<="),
    GREATERTHAN(Associativity.LEFT, ">"),
    GREATERTHAN_EQUAL(Associativity.LEFT, ">="),
    PLUS(Associativity.LEFT, "+"),
    MINUS(Associativity.LEFT, "-"),
    STAR(Associativity.LEFT, "*"),
    SLASH(Associativity.LEFT, "/"),
    PERCENT(Associativity.LEFT, "%"),
    SAFEDIV(Associativity.LEFT, "/?"),
    SAFEREM(Associativity.LEFT, "%?"),
    TILDE(Associativity.LEFT, "~"),
    BANG(Associativity.LEFT, "!"),
    BOOL_AND(Associativity.LEFT, "&"),
    BOOL_OR(Associativity.LEFT, "|"),
    POWER(Associativity.LEFT, "^"),
    QUESTION(Associativity.LEFT, "?"),
    REVERSE_ARROW(Associativity.LEFT, "<-"),
    SINGLE_ARROW(Associativity.LEFT, "->"),
    DOUBLE_ARROW(Associativity.LEFT, "=>"),
    EQUALTO(Associativity.LEFT, "=="),
    NOTEQUALTO(Associativity.LEFT, "!="),
    UNARY_PLUS(Associativity.LEFT, "u +"),
    UNARY_MINUS(Associativity.LEFT, "u -"),
    UNARY_BOOL_BANG(Associativity.LEFT, "u !"),
    ADITION_ASSIGNMENT(Associativity.RIGHT, "+="),
    SUBTRACTION_ASSIGNMENT(Associativity.RIGHT, "-="),
    MULTIPLICATION_ASSIGNMENT(Associativity.RIGHT, "*="),
    DIVISION_ASSIGNMENT(Associativity.RIGHT, "/="),
    REMAINDER_ASSIGNMENT(Associativity.RIGHT, "%="),
    CLOSED_RANGE(Associativity.RIGHT, ".."),
    HALFOPEN_RANGE(Associativity.RIGHT, ".<"),;

    private final Associativity associative;
    private final String image;

    private Operators(Associativity associative, String image) {
        this.associative = associative;
        this.image = image;
    }

    @Override
    public String toString() {
        return image;
    }

    public Associativity getAssociativity() {
        return associative;
    }

    public enum Associativity {

        LEFT,
        RIGHT,
    }
}

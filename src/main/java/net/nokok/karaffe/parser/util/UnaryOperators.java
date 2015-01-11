/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import net.nokok.karaffe.parser.util.Operators.Associativity;

public enum UnaryOperators {

    UNARY_PLUS(Associativity.LEFT, "u +"),
    UNARY_MINUS(Associativity.LEFT, "u -"),
    UNARY_BOOL_BANG(Associativity.LEFT, "u !"),;

    private final Associativity associative;
    private final String image;

    private UnaryOperators(Associativity associative, String image) {
        this.associative = associative;
        this.image = image;
    }
}

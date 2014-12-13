/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import net.nokok.karaffe.parser.Token;

public class TypeParameter {

    private final Token typeVariable;
    private final Operator op;
    private final Token type;

    public TypeParameter(Token var, Operator op, Token type) {
        this.typeVariable = var;
        this.op = op;
        this.type = type;
    }

    public static enum Operator {

        EXTENDS,
        SUPER,
    }
}

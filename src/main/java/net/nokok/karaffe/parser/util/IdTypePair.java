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

public class IdTypePair {

    private final Token id;
    private final Token type;

    public IdTypePair(Token id, Token type) {
        this.id = id;
        this.type = type;
    }
}

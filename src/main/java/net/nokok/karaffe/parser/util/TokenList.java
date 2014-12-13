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

import java.util.ArrayList;
import java.util.List;
import net.nokok.karaffe.parser.Token;

public class TokenList {

    private final List<Token> tokens = new ArrayList<>();

    public void add(Token token) {
        tokens.add(token);
    }
}

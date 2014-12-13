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

public class TypeNameWithTypeParameter {

    private final Token id;
    private final TypeParameter typeParam;

    public TypeNameWithTypeParameter(Token id, TypeParameter node) {
        this.id = id;
        this.typeParam = node;
    }
}

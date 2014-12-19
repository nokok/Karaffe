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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SourceCodeDataPool {

    private final Map<String, IdentifierType> identifiers = new HashMap<>();

    public void addIdentifier(String identifierName, IdentifierType type) {
        identifiers.put(Objects.requireNonNull(identifierName), Objects.requireNonNull(type));
    }

    public IdentifierType getIdentifierType(String identifierName) {
        if ( identifiers.containsKey(identifierName) ) {
            return identifiers.get(identifierName);
        } else {
            return IdentifierType.NONE;
        }
    }
}

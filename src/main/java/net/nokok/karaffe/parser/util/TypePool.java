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
import net.nokok.karaffe.parser.SimpleNode;

public class TypePool {

    private final Map<String, SimpleNode> types = new HashMap<>();

    public void addType(String typeName, SimpleNode node) {
        types.put(typeName, node);
    }
}

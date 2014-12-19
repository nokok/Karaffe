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

import java.util.Optional;
import net.nokok.karaffe.parser.Token;

public class VariableDeclarationData {

    private final Token id;
    private final Optional<Token> type;

    public VariableDeclarationData(Token id, Token type) {
        this.id = id;
        this.type = Optional.ofNullable(type);
    }

    public String identifier() {
        return UnicodeUtil.unicodeToJavaIdentifier(id.image);
    }

    public Class<?> type() {
        if (type.isPresent()) {
            try {
                return Class.forName(type.get().image);
            } catch (ClassNotFoundException ex) {
                return Object.class;
            }
        } else {
            return Object.class;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id.image);
        type.ifPresent(t -> {
            sb.append(":");
            sb.append(t.image);
        });
        return sb.toString();
    }
}

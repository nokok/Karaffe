package net.nokok.karaffe.javacc.ast;

import static java.lang.Math.abs;
import java.nio.charset.Charset;
import static java.util.Objects.requireNonNull;

public abstract class Identifier implements ASTNode {

    protected final String name;

    public Identifier(String name) {
        this.name = requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String javaIdentifier() {
        StringBuilder sb = new StringBuilder("Id_");
        for (byte b : name.getBytes(Charset.forName("UTF-16"))) {
            sb.append(abs(b));
        }
        return sb.toString();
    }
}

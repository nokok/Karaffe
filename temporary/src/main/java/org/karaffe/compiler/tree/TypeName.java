package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.IdentifierToken;

public class TypeName extends TermNode {

    public static TypeName createArrayType(final IdentifierToken typeName) {
        return new TypeName(typeName, true);
    }

    public static TypeName createArrayType(final String typeName) {
        return new TypeName(new IdentifierToken.TypeName(typeName), true);
    }

    public static TypeName createType(final IdentifierToken typeName) {
        return new TypeName(typeName, false);
    }

    public static TypeName createType(final String typeName) {
        return new TypeName(new IdentifierToken.TypeName(typeName), false);
    }

    private final boolean isArrayType;

    public TypeName(final IdentifierToken typeName, final boolean isArray) {
        super(NodeType.TYPENAME, typeName);
        this.isArrayType = isArray;
    }

    public boolean isArray() {
        return this.isArrayType;
    }

    @Override
    public String toString() {
        return String.format("(TypeName %s%s)", super.getText(), this.isArrayType ? "[]" : "");
    }
}

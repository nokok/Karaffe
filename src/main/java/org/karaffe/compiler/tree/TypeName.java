package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class TypeName extends TermNode {

    private final boolean isArrayType;

    public TypeName(final IdentifierToken typeName, final boolean isArray) {
        super(NodeType.TYPENAME, typeName);
        this.isArrayType = isArray;
    }

    public boolean isArray() {
        return this.isArrayType;
    }

    public static TypeName createArrayType(final IdentifierToken typeName) {
        return new TypeName(typeName, true);
    }

    public static TypeName createType(final IdentifierToken typeName) {
        return new TypeName(typeName, false);
    }

    public static TypeName createArrayType(final String typeName) {
        return new TypeName(new IdentifierToken.TypeName(typeName), true);
    }

    public static TypeName createType(final String typeName) {
        return new TypeName(new IdentifierToken.TypeName(typeName), false);
    }

    @Override
    public String toString() {
        return String.format("(TypeName %s%s)", super.getText(), this.isArrayType ? "[]" : "");
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}

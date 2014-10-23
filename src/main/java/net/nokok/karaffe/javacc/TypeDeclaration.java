package net.nokok.karaffe.javacc;

public class TypeDeclaration implements Statement {

    final Type baseName;
    final Type typeName;

    public TypeDeclaration(Type baseName, Type typeName) {
        this.baseName = baseName;
        this.typeName = typeName;
    }

    @Override
    public StatementType getType() {
        return StatementType.TYPE_DECLARATION;
    }
}

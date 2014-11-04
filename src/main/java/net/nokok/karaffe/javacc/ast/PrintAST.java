package net.nokok.karaffe.javacc.ast;

public class PrintAST implements ASTVisitor {

    public static int depth = 0;

    @Override
    public Object onArrayElement(ArrayElement aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onArrayElements(ArrayElements aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onBoolLiteral(BoolLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onEndOfFileStatement(EndOfFileStatement aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onFixedSizeArrayLiteral(FixedSizeArrayLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onFloatLiteral(FloatLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onFunctionLiteral(FunctionLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onIntLiteral(IntLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onJavaTypeLiteral(JavaTypeLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onKaraffeTypeLiteral(KaraffeTypeLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onMissingJavaType(MissingJavaType aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onProgram(Program aThis) {
        printIdentifier(aThis);
        for ( ASTNode node : aThis.getNodes() ) {
            node.accept(this);
        }
        return null;
    }

    @Override
    public Object onPrograms(Programs aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onStringLiteral(StringLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onTypeId(TypeId aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onTypeParameterId(TypeParameterId aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onTypeParameters(TypeParameters aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onUndefinedLiteral(UndefinedLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onVaraibleId(VariableId aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onVariableDeclaration(VariableDeclaration aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onVariableIdTypePair(VariableIdTypePair aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onVariableIdTypePairs(VariableIdTypePairs aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onVariableList(VariableList aThis) {
        printIdentifier(aThis);
        return null;
    }

    private void printIdentifier(ASTNode node) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0; i < depth; i++ ) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(node.nodeIdentifier());
        stringBuilder.append(":");
        stringBuilder.append(node.toString());
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());
        depth++;
    }
}

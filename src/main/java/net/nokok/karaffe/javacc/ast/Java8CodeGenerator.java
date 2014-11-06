package net.nokok.karaffe.javacc.ast;

import java.util.Iterator;

public class Java8CodeGenerator extends ASTVisitorAdapter {

    private final StringBuilder builder = new StringBuilder();

    private String defaultImports() {
        StringBuilder sb = new StringBuilder();
        sb.append("import java.lang.*;").append("\n");
        sb.append("import java.io.*;").append("\n");
        sb.append("import java.lang.*;").append("\n");
        sb.append("import java.math.BigDecimal;").append("\n");
        sb.append("import java.math.BigInteger;").append("\n");
        sb.append("import java.net.*;").append("\n");
        sb.append("import java.util.*;").append("\n");
        sb.append("import karaffe.lang.*;").append("\n");
        return sb.toString();
    }

    @Override
    public Object onProgram(Program aThis) {
        builder.append(defaultImports());
        builder.append("\n");
        builder.append("public class Krf_Main {\n");
        builder.append("  public static void main(java.lang.String... args){\n");
        for (ASTNode node : aThis.getNodes()) {
            builder.append("  ").append(node.accept(this));
        }
        builder.append("  }\n");
        builder.append("}\n");

        return builder.toString();
    }

    @Override
    public Object onIntLiteral(IntLiteral aThis) {
        return "new karaffe.lang.Int(" + aThis.eval(null) + ")";
    }

    @Override
    public Object onFloatLiteral(FloatLiteral aThis) {
        return "new karaffe.lang.Float(" + aThis.eval(null) + ")";
    }

    @Override
    public Object onStringLiteral(StringLiteral aThis) {
        return "new karaffe.lang.String(" + aThis.eval(null) + ")";
    }

    @Override
    public Object onNewLineToken(NewLineToken aThis) {
        return "\n";
    }

    @Override
    public Object onEndOfFileStatement(EndOfFileStatement aThis) {
        return "\n";
    }

    @Override
    public Object onFixedSizeArrayLiteral(FixedSizeArrayLiteral aThis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object onArrayElements(ArrayElements aThis) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator<ArrayElement> iter = aThis.iterator();
        while (iter.hasNext()) {
            ArrayElement next = iter.next();
            sb.append(next.accept(this));
            if (iter.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Object onArrayElement(ArrayElement aThis) {
        return aThis.value.accept(this);
    }

    @Override
    public Object onVariableDeclaration(VariableDeclaration aThis) {
        StringBuilder sb = new StringBuilder();
        sb.append(aThis.getJavaType());
        sb.append(' ');
        sb.append(aThis.getJavaName());
        sb.append(' ')
                .append('=')
                .append(' ')
                .append(aThis.getNode().accept(this))
                .append(';')
                .append('\n');
        return sb.toString();
    }
}

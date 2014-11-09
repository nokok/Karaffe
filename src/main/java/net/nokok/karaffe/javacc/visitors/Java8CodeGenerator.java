package net.nokok.karaffe.javacc.visitors;

import java.util.Iterator;
import net.nokok.karaffe.javacc.ast.ASTNode;
import net.nokok.karaffe.javacc.ast.ASTVisitorAdapter;
import net.nokok.karaffe.javacc.ast.ArrayElement;
import net.nokok.karaffe.javacc.ast.ArrayElements;
import net.nokok.karaffe.javacc.ast.EndOfFileStatement;
import net.nokok.karaffe.javacc.ast.FixedSizeArrayLiteral;
import net.nokok.karaffe.javacc.ast.FloatLiteral;
import net.nokok.karaffe.javacc.ast.IntLiteral;
import net.nokok.karaffe.javacc.ast.NewLineToken;
import net.nokok.karaffe.javacc.ast.Program;
import net.nokok.karaffe.javacc.ast.StringLiteral;
import net.nokok.karaffe.javacc.ast.VariableDeclaration;

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
        return aThis.getValue().accept(this);
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

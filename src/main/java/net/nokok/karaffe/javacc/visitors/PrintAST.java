package net.nokok.karaffe.javacc.visitors;

import net.nokok.karaffe.javacc.ast.ASTNode;
import net.nokok.karaffe.javacc.ast.ASTVisitor;
import net.nokok.karaffe.javacc.ast.ArrayElement;
import net.nokok.karaffe.javacc.ast.ArrayElements;
import net.nokok.karaffe.javacc.ast.BoolLiteral;
import net.nokok.karaffe.javacc.ast.EndOfFileStatement;
import net.nokok.karaffe.javacc.ast.FixedSizeArrayLiteral;
import net.nokok.karaffe.javacc.ast.FloatLiteral;
import net.nokok.karaffe.javacc.ast.FunctionLiteral;
import net.nokok.karaffe.javacc.ast.IntLiteral;
import net.nokok.karaffe.javacc.ast.JavaTypeLiteral;
import net.nokok.karaffe.javacc.ast.KaraffeTypeLiteral;
import net.nokok.karaffe.javacc.ast.MissingJavaType;
import net.nokok.karaffe.javacc.ast.NewLineToken;
import net.nokok.karaffe.javacc.ast.Program;
import net.nokok.karaffe.javacc.ast.Programs;
import net.nokok.karaffe.javacc.ast.StringLiteral;
import net.nokok.karaffe.javacc.ast.TypeId;
import net.nokok.karaffe.javacc.ast.TypeParameterId;
import net.nokok.karaffe.javacc.ast.TypeParameters;
import net.nokok.karaffe.javacc.ast.UndefinedLiteral;
import net.nokok.karaffe.javacc.ast.VariableDeclaration;
import net.nokok.karaffe.javacc.ast.VariableId;
import net.nokok.karaffe.javacc.ast.VariableIdTypePair;
import net.nokok.karaffe.javacc.ast.VariableIdTypePairs;
import net.nokok.karaffe.javacc.ast.VariableList;

public class PrintAST implements ASTVisitor {

    @Override
    public Object onArrayElement(ArrayElement aThis) {
        System.out.println("onArrayElement");
        return aThis.eval(null);
    }

    @Override
    public Object onArrayElements(ArrayElements aThis) {
        System.out.println("onArrayElements");
        return aThis.accept(this);
    }

    @Override
    public Object onBoolLiteral(BoolLiteral aThis) {
        System.out.println("onBoolLiteral");
        return aThis.eval(null);
    }

    @Override
    public Object onEndOfFileStatement(EndOfFileStatement aThis) {
        System.out.println("-EOF-");
        System.out.println("");
        return null;
    }

    @Override
    public Object onFixedSizeArrayLiteral(FixedSizeArrayLiteral aThis) {
        System.out.println("onFixedSizeArrayLiteral");
        for (ArrayElement element : aThis.getValue()) {

        }
        return aThis.eval(null);
    }

    @Override
    public Object onFloatLiteral(FloatLiteral aThis) {
        System.out.println("onFloatLiteral");
        return aThis.eval(null);
    }

    @Override
    public Object onFunctionLiteral(FunctionLiteral aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onIntLiteral(IntLiteral aThis) {
        System.out.println("onIntLiteral");
        return aThis.eval(null);
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
    public Object onNewLineToken(NewLineToken aThis) {
        return "<Nl>";
    }

    @Override
    public Object onProgram(Program aThis) {
        System.out.println("onProgram");
        aThis.getNodes().forEach(n -> n.accept(this));
        return null;
    }

    @Override
    public Object onPrograms(Programs aThis) {
        printIdentifier(aThis);
        return null;
    }

    @Override
    public Object onStringLiteral(StringLiteral aThis) {
        System.out.println("onStringLiteral");
        return aThis.eval(null);
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
        output("onVariableDeclaration");
        output("name = ", aThis.getNameString());
        output("type = ", aThis.getTypeString());
        output(aThis.getNode().accept(this).toString());
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
        stringBuilder.append(node.nodeIdentifier());
        stringBuilder.append(":");
        stringBuilder.append(node.toString());
        System.out.println(stringBuilder.toString());
    }

    private void output(String... values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(value);
        }
        System.out.println(sb.toString());
    }
}

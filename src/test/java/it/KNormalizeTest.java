package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.MethodDefParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.phases.knormalize.KNormalizeVisitor;
import org.karaffe.compiler.tree.base.Node;

public class KNormalizeTest {
    @Test
    public void testSimpleExpr1() {
        testKNormalize(
                "1+2",
                new ExprParser(),
                "(Apply (Select (IntLiteral 1) (Select +)) (IntLiteral 2))",
                "[(Assign (Name kn_0) (IntLiteral 1)),(Assign (Name kn_1) (Select +)),(Assign (Name kn_2) (Select kn_0 kn_1)),(Assign (Name kn_3) (IntLiteral 2)),(Assign (Name kn_4) (Apply (Name kn_2) (Name kn_3)))]");
    }

    @Test
    public void testSimpleExpr2() {
        testKNormalize(
                "1+2*3",
                new ExprParser(),
                "(Apply (Select (IntLiteral 1) (Select +)) (Apply (Select (IntLiteral 2) (Select *)) (IntLiteral 3)))",
                "[(Assign (Name kn_0) (IntLiteral 1)),(Assign (Name kn_1) (Select +)),(Assign (Name kn_2) (Select kn_0 kn_1)),(Assign (Name kn_3) (IntLiteral 2)),(Assign (Name kn_4) (Select *)),(Assign (Name kn_5) (Select kn_3 kn_4)),(Assign (Name kn_6) (IntLiteral 3)),(Assign (Name kn_7) (Apply (Name kn_5) (Name kn_6))),(Assign (Name kn_8) (Apply (Name kn_2) (Name kn_7)))]");
    }

    @Test
    public void testMethodInvocation1() {
        testKNormalize(
                "t.v(1)",
                new ExprParser(),
                "(Apply (Select t v) (IntLiteral 1))",
                "[(Assign (Name kn_0) (Select t v)),(Assign (Name kn_1) (IntLiteral 1)),(Assign (Name kn_2) (Apply (Name kn_0) (Name kn_1)))]");
    }

    @Test
    public void testMethodDef() {
        testKNormalize(
                "    public int computeFac(int num){\n" +
                        "        int numAux ;\n" +
                        "        if (num < 1) {\n" +
                        "            numAux = 1 ;\n" +
                        "        } else {\n" +
                        "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                        "        }" +
                        "        return numAux ;\n" +
                        "    }\n",
                new MethodDefParser(),
                "(MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (ValDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) (Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (If (Apply (Select (Select num) (Select <)) (IntLiteral 1)) (Block (Assign (Name numAux) (IntLiteral 1))) (Block (Assign (Name numAux) (Apply (Select (Select num) (Select *)) (Apply (Apply (Select (ThisLiteral this) ComputeFac) (Apply (Select (Select num) (Select -)) (IntLiteral 1)))))))) (Return (Select numAux))))",
                "(MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (ValDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) [(Block [(VarDef (Modifiers ()) (Name numAux) (TypeName int))] [(Assign (Name kn_0) (Select num)),(Assign (Name kn_1) (Select <)),(Assign (Name kn_2) (Select kn_0 kn_1)),(Assign (Name kn_3) (IntLiteral 1)),(Assign (Name kn_4) (Apply (Name kn_2) (Name kn_3))),(If (Name kn_4) [(Block [(Assign (Name kn_5) (IntLiteral 1)),(Assign (Name numAux) (Name kn_5))])] [(Block [(Assign (Name kn_6) (Select num)),(Assign (Name kn_7) (Select *)),(Assign (Name kn_8) (Select kn_6 kn_7)),(Assign (Name kn_9) (ThisLiteral this)),(Assign (Name kn_10) (Select kn_9 ComputeFac)),(Assign (Name kn_11) (Select num)),(Assign (Name kn_12) (Select -)),(Assign (Name kn_13) (Select kn_11 kn_12)),(Assign (Name kn_14) (IntLiteral 1)),(Assign (Name kn_15) (Apply (Name kn_13) (Name kn_14))),(Assign (Name kn_16) (Apply (Name kn_10) (Name kn_15))),(Assign (Name kn_17) (Apply (Name kn_16))),(Assign (Name kn_18) (Apply (Name kn_8) (Name kn_17))),(Assign (Name numAux) (Name kn_18))])])] [(Assign (Name kn_19) (Select numAux)),(Return (Name kn_19))])])");
    }

    @Test
    public void testCompileUnit() {
        testKNormalize("class Factorial {\r\n" +
                "    public static void main(String[] a) {\r\n" +
                "        System.out.println((new Fac()).computeFac(10));\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "\r\n" +
                "class Fac {\r\n" +
                "    public int computeFac(int num){\r\n" +
                "        int numAux ;\r\n" +
                "        if (num < 1)\r\n" +
                "            numAux = 1;\r\n" +
                "        else\r\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\r\n" +
                "        return numAux;\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "",
                new KaraffeParser(),
                "(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name Factorial) (Name Object) (Block (MethodDef (Modifiers ()) (Name main) (Parameters (ValDef (Modifiers ()) (Name a) (TypeName String[]))) (TypeName void) (Block (Apply (Select java lang System println) (Apply (Select (Apply (Apply (New (Select Fac)))) computeFac) (IntLiteral 10))))))) (ClassDef (Name Fac) (Name Object) (Block (MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (ValDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) (Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (If (Apply (Select (Select num) (Select <)) (IntLiteral 1)) (Assign (Name numAux) (IntLiteral 1)) (Assign (Name numAux) (Apply (Select (Select num) (Select *)) (Apply (Apply (Select (ThisLiteral this) ComputeFac) (Apply (Select (Select num) (Select -)) (IntLiteral 1))))))) (Return (Select numAux)))))))",
                "(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name Factorial) (Name Object) [(Block [(MethodDef (Modifiers ()) (Name main) (Parameters (ValDef (Modifiers ()) (Name a) (TypeName String[]))) (TypeName void) [(Block [(Assign (Name kn_0) (Select java lang System println)),(Assign (Name kn_1) (New (Select Fac))),(Assign (Name kn_2) (Apply (Name kn_1))),(Assign (Name kn_3) (Apply (Name kn_2))),(Assign (Name kn_4) (Select kn_3 computeFac)),(Assign (Name kn_5) (IntLiteral 10)),(Assign (Name kn_6) (Apply (Name kn_4) (Name kn_5))),(Assign (Name kn_7) (Apply (Name kn_0) (Name kn_6)))])])])]) (ClassDef (Name Fac) (Name Object) [(Block [(MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (ValDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) [(Block [(VarDef (Modifiers ()) (Name numAux) (TypeName int))] [(Assign (Name kn_8) (Select num)),(Assign (Name kn_9) (Select <)),(Assign (Name kn_10) (Select kn_8 kn_9)),(Assign (Name kn_11) (IntLiteral 1)),(Assign (Name kn_12) (Apply (Name kn_10) (Name kn_11))),(If (Name kn_12) [(Assign (Name kn_13) (IntLiteral 1)),(Assign (Name numAux) (Name kn_13))] [(Assign (Name kn_14) (Select num)),(Assign (Name kn_15) (Select *)),(Assign (Name kn_16) (Select kn_14 kn_15)),(Assign (Name kn_17) (ThisLiteral this)),(Assign (Name kn_18) (Select kn_17 ComputeFac)),(Assign (Name kn_19) (Select num)),(Assign (Name kn_20) (Select -)),(Assign (Name kn_21) (Select kn_19 kn_20)),(Assign (Name kn_22) (IntLiteral 1)),(Assign (Name kn_23) (Apply (Name kn_21) (Name kn_22))),(Assign (Name kn_24) (Apply (Name kn_18) (Name kn_23))),(Assign (Name kn_25) (Apply (Name kn_24))),(Assign (Name kn_26) (Apply (Name kn_16) (Name kn_25))),(Assign (Name numAux) (Name kn_26))])] [(Assign (Name kn_27) (Select numAux)),(Return (Name kn_27))])])])]))");

    }

    private void testKNormalize(String source, Parser parser, String before, String after) {
        KaraffeLexer lexer = new KaraffeLexer(source);
        Node beforeNode = parser.parse(lexer.run()).getNode().get();
        assertEquals(before, beforeNode.toString());
        KNormalizeVisitor kNormalizeVisitor = new KNormalizeVisitor(beforeNode);
        Node afterNode = kNormalizeVisitor.normalize();
        assertEquals(after, afterNode.toString());
    }
}

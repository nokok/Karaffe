package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.MethodDefParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.base.Node;

public class KNormalizeTest {
    @Test
    public void testCompileUnit() {
        this.testKNormalize("class Factorial {\r\n" +
                "    public static void main(String[] a) {\r\n" +
                "        System.out.println((new Fac()).computeFac(10));\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "\r\n" +
                "class Fac {\r\n" +
                "    public int computeFac(int num){\r\n" +
                "        var numAux :int ;\r\n" +
                "        if (num < 1)\r\n" +
                "            numAux = 1;\r\n" +
                "        else\r\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\r\n" +
                "        return numAux;\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "",
                new KaraffeParser(),
                "(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name Factorial) (Name Object) (Block (MethodDef (Modifiers ()) (Name main) (Parameters (LetDef (Modifiers ()) (Name a) (TypeName String[]))) (TypeName void) (Block (Apply (Select java lang System out println) (Apply (Select (Apply (New (Select Fac))) computeFac) (IntLiteral 10))))))) (ClassDef (Name Fac) (Name Object) (Block (MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (LetDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) (Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (If (Apply (Select (Select num) (Select <)) (IntLiteral 1)) (Assign (Name numAux) (IntLiteral 1)) (Assign (Name numAux) (Apply (Select (Select num) (Select *)) (Apply (Select (ThisLiteral this) ComputeFac) (Apply (Select (Select num) (Select -)) (IntLiteral 1)))))) (Return (Select numAux)))))))",
                "(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name Factorial) (Name Object) [(Block (MethodDef (Modifiers ()) (Name main) (Parameters (LetDef (Modifiers ()) (Name a) (TypeName String[]))) (TypeName void) [(Block (LetDef (Modifiers ()) (Name kn_0) ()) (Assign (Name kn_0) (Select java lang System out println)) (LetDef (Modifiers ()) (Name kn_1) ()) (Assign (Name kn_1) (New (Select Fac))) (LetDef (Modifiers ()) (Name kn_2) ()) (Assign (Name kn_2) (Apply (Name kn_1))) (LetDef (Modifiers ()) (Name kn_3) ()) (Assign (Name kn_3) (Select kn_2 computeFac)) (LetDef (Modifiers ()) (Name kn_4) ()) (Assign (Name kn_4) (IntLiteral 10)) (LetDef (Modifiers ()) (Name kn_5) ()) (Assign (Name kn_5) (Apply (Name kn_3) (Name kn_4))) (LetDef (Modifiers ()) (Name kn_6) ()) (Assign (Name kn_6) (Apply (Name kn_0) (Name kn_5))))]))]) (ClassDef (Name Fac) (Name Object) [(Block (MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (LetDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) [(Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (LetDef (Modifiers ()) (Name kn_7) ()) (Assign (Name kn_7) (Select num)) (LetDef (Modifiers ()) (Name kn_8) ()) (Assign (Name kn_8) (Select <)) (LetDef (Modifiers ()) (Name kn_9) ()) (Assign (Name kn_9) (Select kn_7 kn_8)) (LetDef (Modifiers ()) (Name kn_10) ()) (Assign (Name kn_10) (IntLiteral 1)) (LetDef (Modifiers ()) (Name kn_11) ()) (Assign (Name kn_11) (Apply (Name kn_9) (Name kn_10))) (If (Name kn_11) [(LetDef (Modifiers ()) (Name kn_12) ()),(Assign (Name kn_12) (IntLiteral 1)),(Assign (Name numAux) (Name kn_12))] [(LetDef (Modifiers ()) (Name kn_13) ()),(Assign (Name kn_13) (Select num)),(LetDef (Modifiers ()) (Name kn_14) ()),(Assign (Name kn_14) (Select *)),(LetDef (Modifiers ()) (Name kn_15) ()),(Assign (Name kn_15) (Select kn_13 kn_14)),(LetDef (Modifiers ()) (Name kn_16) ()),(Assign (Name kn_16) (ThisLiteral this)),(LetDef (Modifiers ()) (Name kn_17) ()),(Assign (Name kn_17) (Select kn_16 ComputeFac)),(LetDef (Modifiers ()) (Name kn_18) ()),(Assign (Name kn_18) (Select num)),(LetDef (Modifiers ()) (Name kn_19) ()),(Assign (Name kn_19) (Select -)),(LetDef (Modifiers ()) (Name kn_20) ()),(Assign (Name kn_20) (Select kn_18 kn_19)),(LetDef (Modifiers ()) (Name kn_21) ()),(Assign (Name kn_21) (IntLiteral 1)),(LetDef (Modifiers ()) (Name kn_22) ()),(Assign (Name kn_22) (Apply (Name kn_20) (Name kn_21))),(LetDef (Modifiers ()) (Name kn_23) ()),(Assign (Name kn_23) (Apply (Name kn_17) (Name kn_22))),(LetDef (Modifiers ()) (Name kn_24) ()),(Assign (Name kn_24) (Apply (Name kn_15) (Name kn_23))),(Assign (Name numAux) (Name kn_24))]) (LetDef (Modifiers ()) (Name kn_25) ()) (Assign (Name kn_25) (Select numAux)) (Return (Name kn_25)))]))]))");

    }

    private void testKNormalize(final String source, final Parser parser, final String before, final String after) {
        final Node beforeNode = parser.parse(source).getNode().get();
        assertEquals(before, beforeNode.toString());
        final NodeList afterNode = beforeNode.normalize(new NormalizeContext());
        assertEquals(after, afterNode.toSimpleNode().toString());
    }

    @Test
    public void testMethodDef() {
        this.testKNormalize(
                "    public int computeFac(int num){\n" +
                        "        var numAux: int\n" +
                        "        if (num < 1) {\n" +
                        "            numAux = 1 ;\n" +
                        "        } else {\n" +
                        "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                        "        }" +
                        "        return numAux ;\n" +
                        "    }\n",
                new MethodDefParser(),
                "(MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (LetDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) (Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (If (Apply (Select (Select num) (Select <)) (IntLiteral 1)) (Block (Assign (Name numAux) (IntLiteral 1))) (Block (Assign (Name numAux) (Apply (Select (Select num) (Select *)) (Apply (Select (ThisLiteral this) ComputeFac) (Apply (Select (Select num) (Select -)) (IntLiteral 1))))))) (Return (Select numAux))))",
                "(MethodDef (Modifiers (Modifier public)) (Name computeFac) (Parameters (LetDef (Modifiers (Modifier )) (Name num) (TypeName int))) (TypeName int) [(Block (VarDef (Modifiers ()) (Name numAux) (TypeName int)) (LetDef (Modifiers ()) (Name kn_0) ()) (Assign (Name kn_0) (Select num)) (LetDef (Modifiers ()) (Name kn_1) ()) (Assign (Name kn_1) (Select <)) (LetDef (Modifiers ()) (Name kn_2) ()) (Assign (Name kn_2) (Select kn_0 kn_1)) (LetDef (Modifiers ()) (Name kn_3) ()) (Assign (Name kn_3) (IntLiteral 1)) (LetDef (Modifiers ()) (Name kn_4) ()) (Assign (Name kn_4) (Apply (Name kn_2) (Name kn_3))) (If (Name kn_4) [(Block (LetDef (Modifiers ()) (Name kn_5) ()) (Assign (Name kn_5) (IntLiteral 1)) (Assign (Name numAux) (Name kn_5)))] [(Block (LetDef (Modifiers ()) (Name kn_6) ()) (Assign (Name kn_6) (Select num)) (LetDef (Modifiers ()) (Name kn_7) ()) (Assign (Name kn_7) (Select *)) (LetDef (Modifiers ()) (Name kn_8) ()) (Assign (Name kn_8) (Select kn_6 kn_7)) (LetDef (Modifiers ()) (Name kn_9) ()) (Assign (Name kn_9) (ThisLiteral this)) (LetDef (Modifiers ()) (Name kn_10) ()) (Assign (Name kn_10) (Select kn_9 ComputeFac)) (LetDef (Modifiers ()) (Name kn_11) ()) (Assign (Name kn_11) (Select num)) (LetDef (Modifiers ()) (Name kn_12) ()) (Assign (Name kn_12) (Select -)) (LetDef (Modifiers ()) (Name kn_13) ()) (Assign (Name kn_13) (Select kn_11 kn_12)) (LetDef (Modifiers ()) (Name kn_14) ()) (Assign (Name kn_14) (IntLiteral 1)) (LetDef (Modifiers ()) (Name kn_15) ()) (Assign (Name kn_15) (Apply (Name kn_13) (Name kn_14))) (LetDef (Modifiers ()) (Name kn_16) ()) (Assign (Name kn_16) (Apply (Name kn_10) (Name kn_15))) (LetDef (Modifiers ()) (Name kn_17) ()) (Assign (Name kn_17) (Apply (Name kn_8) (Name kn_16))) (Assign (Name numAux) (Name kn_17)))]) (LetDef (Modifiers ()) (Name kn_18) ()) (Assign (Name kn_18) (Select numAux)) (Return (Name kn_18)))])");
    }

    @Test
    public void testMethodInvocation1() {
        this.testKNormalize(
                "t.v(1)",
                new ExprParser(),
                "(Apply (Select t v) (IntLiteral 1))",
                "[(LetDef (Modifiers ()) (Name kn_0) ()),(Assign (Name kn_0) (Select t v)),(LetDef (Modifiers ()) (Name kn_1) ()),(Assign (Name kn_1) (IntLiteral 1)),(LetDef (Modifiers ()) (Name kn_2) ()),(Assign (Name kn_2) (Apply (Name kn_0) (Name kn_1)))]");
    }

    @Test
    public void testSimpleExpr1() {
        this.testKNormalize(
                "1+2",
                new ExprParser(),
                "(Apply (Select (IntLiteral 1) (Select +)) (IntLiteral 2))",
                "[(LetDef (Modifiers ()) (Name kn_0) ()),(Assign (Name kn_0) (IntLiteral 1)),(LetDef (Modifiers ()) (Name kn_1) ()),(Assign (Name kn_1) (Select +)),(LetDef (Modifiers ()) (Name kn_2) ()),(Assign (Name kn_2) (Select kn_0 kn_1)),(LetDef (Modifiers ()) (Name kn_3) ()),(Assign (Name kn_3) (IntLiteral 2)),(LetDef (Modifiers ()) (Name kn_4) ()),(Assign (Name kn_4) (Apply (Name kn_2) (Name kn_3)))]");
    }

    @Test
    public void testSimpleExpr2() {
        this.testKNormalize(
                "1+2*3",
                new ExprParser(),
                "(Apply (Select (IntLiteral 1) (Select +)) (Apply (Select (IntLiteral 2) (Select *)) (IntLiteral 3)))",
                "[(LetDef (Modifiers ()) (Name kn_0) ()),(Assign (Name kn_0) (IntLiteral 1)),(LetDef (Modifiers ()) (Name kn_1) ()),(Assign (Name kn_1) (Select +)),(LetDef (Modifiers ()) (Name kn_2) ()),(Assign (Name kn_2) (Select kn_0 kn_1)),(LetDef (Modifiers ()) (Name kn_3) ()),(Assign (Name kn_3) (IntLiteral 2)),(LetDef (Modifiers ()) (Name kn_4) ()),(Assign (Name kn_4) (Select *)),(LetDef (Modifiers ()) (Name kn_5) ()),(Assign (Name kn_5) (Select kn_3 kn_4)),(LetDef (Modifiers ()) (Name kn_6) ()),(Assign (Name kn_6) (IntLiteral 3)),(LetDef (Modifiers ()) (Name kn_7) ()),(Assign (Name kn_7) (Apply (Name kn_5) (Name kn_6))),(LetDef (Modifiers ()) (Name kn_8) ()),(Assign (Name kn_8) (Apply (Name kn_2) (Name kn_7)))]");
    }
}

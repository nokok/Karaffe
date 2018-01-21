package org.karaffe.compiler.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.parser.ExprParser.Primary;
import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Constant;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeName;

public class NodeStringTest {
    @Test
    public void testTree() {
        Assert.assertEquals("(Apply (Select a))", new Apply(new Select(new Name("a"))).toString());
    }

    @Test
    public void testTermNodes() {
        Assert.assertEquals("(Constant 1)", new Constant(new LiteralToken.IntLiteral("1")).toString());
        Assert.assertEquals("(IntLiteral 1)", new Literal.IntLiteral(new LiteralToken.IntLiteral("1")).toString());
        Assert.assertEquals("(Modifier public)", new Modifier(new ModifierToken.Public()).toString());
        Assert.assertEquals("(Name name)", new Name("name").toString());
        Assert.assertEquals("(TypeName TName[])", new TypeName(new IdentifierToken.TypeName("TName"), true).toString());
        Assert.assertEquals("(TypeName TName)", new TypeName(new IdentifierToken.TypeName("TName"), false).toString());
    }

    @Test
    public void testExprString() {
        Assert.assertEquals("(IntLiteral 1)", this.getNodeString(new Primary(), "1"));
        Assert.assertEquals("(ThisLiteral this)", this.getNodeString(new Primary(), "this"));
        Assert.assertEquals("(TrueLiteral true)", this.getNodeString(new Primary(), "true"));
        Assert.assertEquals("(FalseLiteral false)", this.getNodeString(new Primary(), "false"));
        Assert.assertEquals("(Apply (New (Select IntArray)) (IntLiteral 1))", this.getNodeString(new Primary(), "new int[1]"));
        Assert.assertEquals("(Apply (Select (Select num) (Select <)) (IntLiteral 1))", this.getNodeString(new ExprParser(), "num < 1"));
        Assert.assertEquals("(Apply (New (Select Fac)))", this.getNodeString(new ExprParser(), "new Fac()"));
        Assert.assertEquals("(Select System)", this.getNodeString(new ExprParser(), "System"));
        // 3.*(1+(2))
        // (Apply (Select (Literal 1) (Select +)) (Apply (Select (Literal 2)
        // (Select *)) (Literal 3)))
        Assert.assertEquals("(Apply (Select (IntLiteral 1) (Select +)) (Apply (Select (IntLiteral 2) (Select *)) (IntLiteral 3)))", this.getNodeString(new ExprParser(), "1+2*3"));
        assertEquals("(Apply (Select negate) (Apply (Select negate) (TrueLiteral true)))", this.getNodeString(new ExprParser(), "!!true"));
    }

    @Test
    public void testExprString2() {
        // 1+2+3+4
        // 1+(2+(3+(4)))
        Assert.assertEquals("(Apply (Select (Apply (Apply (Select (Apply (Apply (Select (IntLiteral 1) (Select +)) (IntLiteral 2))) (Select +)) (IntLiteral 3))) (Select +)) (IntLiteral 4))", this.getNodeString(new ExprParser(), "1+2+3+4"));
    }

    @Test
    public void testStmtString() {
        Assert.assertEquals("(Apply (Select java lang System out println) (IntLiteral 10))", this.getNodeString(new StatementParser(), "System.out.println(10);"));
        Assert.assertEquals("(Apply (Select java lang System out println) (Apply (Select (Apply (New (Select Fac))) computeFac) (IntLiteral 10)))", this.getNodeString(new StatementParser(), "System.out.println((new Fac()).computeFac(10));"));
    }

    @Test
    public void testName() {
        Assert.assertEquals("(Apply (Select a))", new Apply(new Select(new Name("a"))).toString());
    }

    @Test
    public void testApplyArg() {
        Assert.assertEquals("(Apply (Select a) (IntLiteral 1))", new Apply(new Select(new Name("a")), new Literal.IntLiteral(new LiteralToken.IntLiteral("1"))).toString());
    }

    @Test
    public void testCompileUnit() {
        Assert.assertEquals("(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name A) (Name Object) (Block ())))", this.getNodeString(new KaraffeParser(), "class A {}"));
        Assert.assertEquals("(CompileUnit (PackageDef (Select <root>)) (ClassDef (Name B) (Name Object) (Block ())))", this.getNodeString(new KaraffeParser(), "class B {}"));
        Assert.assertEquals("(CompileUnit (PackageDef (Select foo)) (ClassDef (Name A) (Name Object) (Block ())))", this.getNodeString(new KaraffeParser(), "package foo;class A {}"));
        Assert.assertEquals("(CompileUnit (PackageDef (Select foo bar)) (ClassDef (Name A) (Name Object) (Block ())))", this.getNodeString(new KaraffeParser(), "package foo.bar;class A {}"));
        Assert.assertEquals("(CompileUnit (PackageDef (Select foo bar)) (ClassDef (Name A) (Name Base) (Block ())))", this.getNodeString(new KaraffeParser(), "package foo.bar;class A extends Base {}"));
    }

    @Test
    public void testNodeList() {
        Assert.assertEquals("[(Apply (Select a))]", new NodeList(new Apply(new Select(new Name("a")))).toString());
        Assert.assertEquals("[(Apply (Select a)),(Apply (Select a) (IntLiteral 1))]", new NodeList(new Apply(new Select(new Name("a"))), new Apply(new Select(new Name("a")), new Literal.IntLiteral(new LiteralToken.IntLiteral("1")))).toString());
    }

    @Test
    public void testNestedExprTest() {
        Assert.assertEquals("(Apply (Select (Apply (New (Select Fac))) computeFac) (IntLiteral 10))", this.getNodeString(new ExprParser(), "(new Fac()).computeFac(10)"));
    }

    private String getNodeString(final Parser parser, final String source) {
        return parser.parse(new KaraffeLexer(source).run()).getNode().get().toString();
    }

}

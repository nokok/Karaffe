package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.Arrays;

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
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.base.AbstractNode;

public class NodeStringTest {
    @Test
    public void testTree() {
        Assert.assertEquals("(Apply (Select (Name a)))", new Apply(new Select(new Name("a"))).toString());
    }

    @Test
    public void testTermNodes() {
        Assert.assertEquals("(Constant 1)", new Constant(new LiteralToken.IntLiteral("1")).toString());
        Assert.assertEquals("(Literal 1)", new Literal(new LiteralToken.IntLiteral("1")).toString());
        Assert.assertEquals("(Modifier public)", new Modifier(new ModifierToken.Public()).toString());
        Assert.assertEquals("(Name name)", new Name("name").toString());
        Assert.assertEquals("(TypeName TName[])", new TypeName(new IdentifierToken.TypeName("TName"), true).toString());
        Assert.assertEquals("(TypeName TName)", new TypeName(new IdentifierToken.TypeName("TName"), false).toString());
    }

    @Test
    public void testExprString() {
        Assert.assertEquals("(Literal 1)", this.getNodeString(new Primary(), "1"));
        Assert.assertEquals("(Literal this)", this.getNodeString(new Primary(), "this"));
        Assert.assertEquals("(Literal true)", this.getNodeString(new Primary(), "true"));
        Assert.assertEquals("(Literal false)", this.getNodeString(new Primary(), "false"));
        Assert.assertEquals("(Apply (New (Select (Name IntArray))) (Literal 1))", this.getNodeString(new Primary(), "new int[1]"));
        Assert.assertEquals("(Apply (Select (Name num) (Select (Name <))) (Literal 1))", this.getNodeString(new ExprParser(), "num < 1"));
        Assert.assertEquals("(Apply (New (Select (Name Fac))))", this.getNodeString(new ExprParser(), "new Fac()"));
        Assert.assertEquals("(Apply (Apply (Select (Apply (Apply (New (Select (Name Fac))))) (Name computeFac)) (Literal 10)))", this.getNodeString(new ExprParser(), "(new Fac()).computeFac(10)"));
        // 3.*(1+(2))
        // (Apply (Select (Literal 1) (Select (Name +))) (Apply (Select (Literal 2)
        // (Select (Name *))) (Literal 3)))
        Assert.assertEquals("(Apply (Select (Literal 1) (Select (Name +))) (Apply (Select (Literal 2) (Select (Name *))) (Literal 3)))", this.getNodeString(new ExprParser(), "1+2*3"));

    }

    @Test
    public void testExprString2() {
        // 1+2+3+4
        // 1+(2+(3+(4)))
        Assert.assertEquals("(Apply (Select (Apply (Apply (Select (Apply (Apply (Select (Literal 1) (Select (Name +))) (Literal 2))) (Select (Name +))) (Literal 3))) (Select (Name +))) (Literal 4))", this.getNodeString(new ExprParser(), "1+2+3+4"));
    }

    @Test
    public void testStmtString() {
        Assert.assertEquals("(Apply (Select (Name java) (Name lang) (Name System) (Name println)) (Literal 10))", this.getNodeString(new StatementParser(), "System.out.println(10);"));
        Assert.assertEquals("(Apply (Select (Name java) (Name lang) (Name System) (Name println)) (Apply (Apply (Select (Apply (Apply (New (Select (Name Fac))))) (Name computeFac)) (Literal 10))))", this.getNodeString(new StatementParser(), "System.out.println((new Fac()).computeFac(10));"));
    }

    @Test
    public void testAbstractNodeString() {
        final AbstractNode node = new AbstractNode(NodeType.APPLY, new ArrayList<>(Arrays.asList(new Name("name1"), new Name("name2")))) {
        };
        Assert.assertEquals("( (Name name1) (Name name2))", node.toString());
    }

    @Test
    public void testName() {
        Assert.assertEquals("(Apply (Select (Name a)))", new Apply(new Select(new Name("a"))).toString());
    }

    @Test
    public void testApplyArg() {
        Assert.assertEquals("(Apply (Select (Name a)) (Literal 1))", new Apply(new Select(new Name("a")), new Literal(new LiteralToken.IntLiteral("1"))).toString());
    }

    private String getNodeString(final Parser parser, final String source) {
        return parser.parse(new KaraffeLexer(source).run()).getNode().get().toString();
    }

}

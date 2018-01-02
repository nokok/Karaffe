package org.karaffe.compiler.lexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.Dot;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.IdentifierToken.TypeName;
import org.karaffe.compiler.lexer.IdentifierToken.VarName;
import org.karaffe.compiler.lexer.KeywordToken.New;
import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.LiteralToken.IntLiteral;
import org.karaffe.compiler.lexer.ModifierToken.Private;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.ModifierToken.Static;
import org.karaffe.compiler.lexer.OperatorToken.Equals;
import org.karaffe.compiler.lexer.OperatorToken.GreaterThan;
import org.karaffe.compiler.lexer.OperatorToken.LessThan;
import org.karaffe.compiler.lexer.OperatorToken.Minus;
import org.karaffe.compiler.lexer.OperatorToken.Plus;
import org.karaffe.compiler.lexer.OperatorToken.Slash;
import org.karaffe.compiler.lexer.OperatorToken.Star;
import org.karaffe.compiler.lexer.WhitespaceToken.CR;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class KaraffeLexerTest {

    @Before
    public void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.TRACE);
    }

    @Test
    public void testEmpty() {
        final Lexer lexer = new KaraffeLexer("");
        final List<Token> tokens = lexer.run();
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals(WhitespaceToken.EOF.class, tokens.get(0).getClass());
    }

    @Test
    public void testSimpleClassDef() {
        final Lexer lexer = new KaraffeLexer("class A");
        final List<Token> tokens = lexer.run();
        Assert.assertEquals(4, tokens.size());
        Assert.assertEquals(KeywordToken.Class.class, tokens.get(0).getClass());
        Assert.assertEquals(WhitespaceToken.Space.class, tokens.get(1).getClass());
        Assert.assertEquals(IdentifierToken.TypeName.class.getName(), tokens.get(2).getClass().getName());
        Assert.assertEquals(WhitespaceToken.EOF.class, tokens.get(3).getClass());
        final Token idToken = tokens.get(2);
        Assert.assertEquals("A", idToken.getText());
        Assert.assertEquals(1, (int) idToken.getPosition().getLineF().orElse(-1));
        Assert.assertEquals(6, (int) idToken.getPosition().getColumnF().orElse(-1));
    }

    @Test
    public void testLex() {

        final Map<String, Class<? extends Token>> map = new HashMap<>();
        map.put("Hoge", TypeName.class);
        map.put("var", VarName.class);
        map.put("class", KeywordToken.Class.class);
        map.put("package", Package.class);
        map.put("new", New.class);
        map.put("0", IntLiteral.class);
        map.put("1", IntLiteral.class);
        map.put("250", IntLiteral.class);
        map.put("public", Public.class);
        map.put("static", Static.class);
        map.put("private", Private.class);
        map.put("+", Plus.class);
        map.put("-", Minus.class);
        map.put("*", Star.class);
        map.put("/", Slash.class);
        map.put("<", LessThan.class);
        map.put(">", GreaterThan.class);
        map.put(".", Dot.class);
        map.put("=", Equals.class);
        map.put(";", Semi.class);
        for (final Map.Entry<String, Class<? extends Token>> m : map.entrySet()) {
            this.testToken(m.getKey(), m.getValue());
        }
    }

    @Test
    public void testIdentifierToken1() {
        this.testToken("classf", VarName.class);
    }

    @Test
    public void testIdentifierToken2() {
        this.testToken("newint", VarName.class);
    }

    @Test
    public void testIdentifierToken3() {
        this.testToken("fooclass", VarName.class);
    }

    @Test
    public void testIdentifierToken4() {
        this.testToken("Idclass", TypeName.class);
    }

    @Test
    public void testCR() {
        this.testToken("\r", CR.class);
    }

    private void testToken(final String token, final Class<? extends Token> clazz) {
        final List<Token> tokens = new KaraffeLexer("", token, true, true, false).run();
        Assert.assertEquals("1Token : " + tokens, 2, tokens.size());
        Assert.assertTrue("2Token : " + tokens.get(0), tokens.get(0).is(clazz));
        Assert.assertTrue("3Token : " + tokens.get(1), tokens.get(1).is(EOF.class));
    }

}

package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;

public class EOFParserTest {
    @Test
    public void testEOF1() {
        final Parser eof = new EOFParser();
        final MatchResult result = eof.parse(new ArrayList<>());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF2() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer("");
        final MatchResult result = eof.parse(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF3() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer("\n");
        final MatchResult result = eof.parse(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF4() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer("  \t\t\r\n");
        final MatchResult result = eof.parse(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF5() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer("a ");
        final MatchResult result = eof.parse(lexer.run());
        Assert.assertEquals(false, result.isSuccess());
    }

    @Test
    public void testEOF6() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer(" b");
        final MatchResult result = eof.parse(lexer.run());
        Assert.assertEquals(false, result.isSuccess());
    }

    @Test
    public void testEOF7() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer(" b");
        final List<Token> input = lexer.run();
        input.add(new CommonToken.ErrorToken("ERROR"));
        final MatchResult result = eof.parse(input);
        Assert.assertEquals(false, result.isSuccess());
    }
}

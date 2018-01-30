package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.ResultState;

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
        final MatchResult result = eof.parse("");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF3() {
        final Parser eof = new EOFParser();
        final MatchResult result = eof.parse("\n");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF4() {
        final Parser eof = new EOFParser();
        final MatchResult result = eof.parse("  \t\t\r\n");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testEOF5() {
        final Parser eof = new EOFParser();
        final ResultState result = eof.parse("a ");
        Assert.assertEquals(false, result.isSuccess());
    }

    @Test
    public void testEOF6() {
        final Parser eof = new EOFParser();
        final ResultState result = eof.parse(" b");
        Assert.assertEquals(false, result.isSuccess());
    }

    @Test
    public void testEOF7() {
        final Parser eof = new EOFParser();
        final KaraffeLexer lexer = new KaraffeLexer(" b");
        final List<Token> input = lexer.run();
        input.add(new CommonToken.ErrorToken("ERROR"));
        final ResultState result = eof.parse(input);
        Assert.assertEquals(false, result.isSuccess());
    }
}

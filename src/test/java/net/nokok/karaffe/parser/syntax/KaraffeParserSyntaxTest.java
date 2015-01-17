/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.Parser;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KaraffeParserSyntaxTest {

    @Test
    public void testEmptyFile() {
        ASTCompileUnit compileUnit = testCode("");
        assertThat(compileUnit.jjtGetNumChildren(), is(0));
    }

    @Test
    public void testComment() {
        ASTCompileUnit compileUnit = testCode("/*  /* */  */");
        assertThat(compileUnit.jjtGetNumChildren(), is(0));
    }

    @Test
    public void testSingleLineComment() {
        ASTCompileUnit compileUnit = testCode("//hoge");
        assertThat(compileUnit.jjtGetNumChildren(), is(0));
    }

    @Test
    public void testEmptyMultiLineComment() {
        ASTCompileUnit compileUnit = testCode("/**/");
        assertThat(compileUnit.jjtGetNumChildren(), is(0));
    }

    @Test
    public void testNestedMultiLineComment() {
        ASTCompileUnit compileUnit = testCode("/*/**/*/");
        assertThat(compileUnit.jjtGetNumChildren(), is(0));
    }

    public static ASTCompileUnit testCode(String code) {
        try {
            return new Parser(code).CompileUnit();
        } catch (ParseException ex) {
            fail(ex.getMessage());
            return null;
        }
    }

}

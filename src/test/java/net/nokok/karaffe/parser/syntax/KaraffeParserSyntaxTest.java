/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.ParseException;
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
//
//    @Test
//    public void testNewLineToken() {
//        ASTCompileUnit compileUnit = testCode("\n");
//        assertThat(compileUnit.jjtGetNumChildren(), is(1));
//        assertThat(compileUnit.jjtGetChild(0).getClass().getName(), is(ASTNewLineToken.class.getName()));
//    }

//    @Test(timeout = 50)
//    public void testNewLineTokens() {
//        ASTCompileUnit compileUnit = testCode("\n\n\n\n");
//        assertThat(compileUnit.jjtGetNumChildren(), is(4));
//        String astNewLineTokenClassName = ASTNewLineToken.class.getName();
//        for (int i = 0; i < 4; i++) {
//            assertThat(compileUnit.jjtGetChild(i).getClass().getName(), is(astNewLineTokenClassName));
//        }
//    }
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
//
//    @Test
//    public void testSingleLineCommentNewLine() {
//        ASTCompileUnit compileUnit = testCode("//hogehoge\n");
//        assertThat(compileUnit.jjtGetNumChildren(), is(1));
//        assertThat(compileUnit.jjtGetChild(0).getClass().getName(), is(ASTNewLineToken.class.getName()));
//    }

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
            return new KaraffeParser(code).CompileUnit();
        } catch (ParseException ex) {
            fail(ex.getMessage());
            return null;
        }
    }
}

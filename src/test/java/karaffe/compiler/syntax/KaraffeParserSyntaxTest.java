/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import java.io.StringReader;
import karaffe.compiler.phase.parser.parser;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KaraffeParserSyntaxTest {

    @Test
    public void testEmptyFile() {
        testCode("");
    }

    @Test
    public void testComment() {
        testCode("/*  /* */  */");
    }

    @Test
    public void testSingleLineComment() {
        testCode("//hoge");
    }

    @Test
    public void testEmptyMultiLineComment() {
        testCode("/**/");
    }

    @Test
    public void testNestedMultiLineComment() {
        testCode("/*/**/*/");
    }

    public static void testCode(String code) {
        try {
            parser parser = new parser(new StringReader(code));
            parser.parse();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

}

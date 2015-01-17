/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class ImportStmtSyntaxTest {

    @Test
    public void testKaraffeTypeImport() {
        testCode("import ModuleName");
    }

    @Test
    public void testJavaTypeImport() {
        testCode("import java.lang.Object\n");
    }

    @Test
    public void testTypeAliasImport() {
        testCode("import ModuleName -> N");
    }

    @Test
    public void testImports() {
        testCode("import ModuleName1 ModuleName2");
    }

    @Test
    public void testTypeAliasImports() {
        testCode("import ModuleName1 -> M1 ModuleName2 -> M2");
    }

}

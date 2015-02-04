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

    @Test
    public void testPrivateImportInClass() {
        testCode("type Hoge{\n"
                + "import hoge\n"
                + "}");
    }

    @Test
    public void testPrivateImportBlock() {
        testCode("type Hoge{\n"
                + "func this(){\n"
                + "import hoge {\n"
                + "}"
                + "}"
                + "}");
    }

    @Test
    public void testPrivateImportInFunc() {
        testCode("type Hoge{\n"
                + "func hoge() {\n"
                + "import hoge\n"
                + "}\n"
                + "}");
    }

    @Test
    public void testURLImport() {
        testCode("import \"github.com/nokok/karaffe\"");
    }

    @Test
    public void testURLImports() {
        testCode("import \"github.com/nokok/hoge\" \"github.com/nokok/fuga\"");
    }
}

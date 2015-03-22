/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
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
        testCode("import {\n"
                + "\"github.com/nokok/hoge\" \"github.com/nokok/fuga\"\n"
                + "}");
    }

    @Test
    public void testSimpleImports() {
        testCode("import {\n"
                + "java.lang.Object\n"
                + "java.lang.Integer\n"
                + "}\n");
    }

    @Test
    public void testAliasImports() {
        testCode("import {\n"
                + "java.lang.Object -> Object\n"
                + "java.lang.Integer -> Integer\n"
                + "}\n");
    }
}

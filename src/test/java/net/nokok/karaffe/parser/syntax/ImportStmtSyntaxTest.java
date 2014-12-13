/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
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
        testCode("import java.lang.Object");
    }

    @Test
    public void testTypeAliasImport() {
        testCode("import ModuleName -> N");
    }

    @Test
    public void testPrivateImport() {
        testCode("import ModuleName {"
                + ""
                + "}");
    }

    @Test
    public void testTypeAliasPrivateImport() {
        testCode("import ModuleName -> M {"
                + ""
                + "}");
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
    public void testTypeAliasPrivateImports() {
        testCode("import ModuleName1 -> M1 ModuleName2 -> M2{"
                + ""
                + "}");
    }
}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.TestUtil.testCode;
import org.junit.Test;

public class ImportDeclTest {

    @Test
    public void testSimpleImport() {
        testCode("import hoge");
    }

    @Test
    public void testSimpleImportName() {
        testCode("import hoge.piyo");
    }

    @Test
    public void testBlockImport() {
        testCode("import {" //BlockImport
                + "java.lang.Integer " //SimpleImport
                + "java.lang.{Long, Double} " //GroupImport
                + "java.lang.Integer -> Int" //AliasImport
                + "}");
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import static karaffe.compiler.phase.parser.TestUtil.testCode;
import org.junit.Test;

public class PackageDeclTest {

    @Test
    public void testPackageDecl() {
        testCode("package hoge");
    }

    @Test
    public void testPackageDeclName() {
        testCode("package hoge.fuga");
    }
}

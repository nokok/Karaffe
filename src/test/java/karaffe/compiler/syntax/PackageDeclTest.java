/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.TestUtil.testCode;
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

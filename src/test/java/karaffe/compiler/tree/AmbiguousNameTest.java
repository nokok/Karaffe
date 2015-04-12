/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import static karaffe.compiler.phase.parser.TestUtil.testCode;
import karaffe.compiler.tree.compileunits.PackageDecl;
import karaffe.compiler.visitor.VisitorAdaptor;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AmbiguousNameTest {

    @Test
    public void testToPath() {
        final String packageName = "hoge.fuga.piyo";
        AST compileUnit = testCode("package " + packageName);
        compileUnit.accept(new VisitorAdaptor() {

            @Override
            public void packageDecl(PackageDecl packageDecl) {
                AmbiguousName name = packageDecl.name();
                assertThat(name.toPath(), is(packageName));
            }

        });
    }
}

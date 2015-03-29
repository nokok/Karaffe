/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import java.io.StringReader;
import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.phase.parser.Parser;
import karaffe.compiler.tree.CompileUnit;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.visitor.VisitorAdaptor;
import static org.junit.Assert.fail;

public class TestUtil {

    public static void testCode(String code) {
        Parser parser = new Parser(new Lexer(new StringReader(code)));
        try {
            CompileUnit compileUnit = parser.compileUnit();
            compileUnit.accept(new VisitorAdaptor() {

                @Override
                public void errorNode(ErrorNode aThis) {
                    fail(aThis.toString());
                }

            });
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}

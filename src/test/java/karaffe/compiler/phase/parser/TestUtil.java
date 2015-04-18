/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.StringReader;
import java.util.Optional;
import java.util.function.Function;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.visitor.VisitorAdaptor;
import static org.junit.Assert.fail;

public class TestUtil {

    public static AST testCode(String code) {
        try {
            Optional<AST> maybeCompileUnit = testCodeWithoutErrorCheck(code);
            AST compileUnit = maybeCompileUnit.orElseThrow(AssertionError::new);
            compileUnit.accept(new VisitorAdaptor() {

                @Override
                public void errorNode(ErrorNode aThis) {
                    if (aThis == null) {
                        fail();
                        return;
                    }
                    fail(aThis.toString());
                }

            });
            return compileUnit;
        } catch (Throwable e) {
            e.printStackTrace();
            fail(e.toString());
            return null; //unreachable stmt
        }
    }

    public static Optional<AST> testCodeWithoutErrorCheck(String code) {
        Parser parser = new Parser(new Lexer(new StringReader(code)));
        parser.setPath("TEST_CODE");
        try {
            AST compileUnit = parser.compileUnit();
            return Optional.of(compileUnit);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void assertOutput(Function function, String output) {

    }
}

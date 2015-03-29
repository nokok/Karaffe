/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.StringReader;
import java.util.Optional;
import karaffe.compiler.phase.parser.Lexer;
import karaffe.compiler.phase.parser.Parser;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.compileunits.CompileUnit;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.visitor.VisitorAdaptor;
import static org.junit.Assert.fail;

public class TestUtil {

    public static AST testCode(String code) {
        Optional<AST> maybeCompileUnit = testCodeWithoutErrorCheck(code);
        AST compileUnit = maybeCompileUnit.orElseThrow(AssertionError::new);
        compileUnit.accept(new VisitorAdaptor() {

            @Override
            public void errorNode(ErrorNode aThis) {
                if (aThis == null) {
                    return;
                }
                fail(aThis.toString());
            }

        });
        return compileUnit;
    }

    public static Optional<AST> testCodeWithoutErrorCheck(String code) {
        Parser parser = new Parser(new Lexer(new StringReader(code)));
        try {
            CompileUnit compileUnit = parser.compileUnit();
            return Optional.of(compileUnit);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

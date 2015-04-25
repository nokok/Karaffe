/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.File;
import java.io.FileReader;
import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.compileunits.CompileUnit;

public class ParserPhase extends Phase<String, AST> {

    public ParserPhase() {
        super("parser");
    }

    @Override
    public AST apply(String path) {
        try {
            Parser parser = new Parser(new Lexer(new FileReader(path))); //filepath
            parser.setPath(path);
            AST ast = parser.compileUnit();
            return ast;
        } catch (Exception ex) {
            return new CompileUnit(new File("."), null, null, null);
        }

    }

}

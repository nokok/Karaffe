/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.FileReader;
import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;

public class ParserPhase extends Phase<String, AST> {

    @Override
    public AST apply(String path) {
        try {
            Parser parser = new Parser(new Lexer(new FileReader(path))); //filepath
            parser.setPath(path);
            AST ast = parser.compileUnit();
            return ast;
        } catch (Exception ex) {
            ex.printStackTrace(); //debug
            return null;
        }

    }

}

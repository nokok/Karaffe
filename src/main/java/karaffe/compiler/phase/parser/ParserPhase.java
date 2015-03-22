/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.FileReader;
import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;
import karaffe.core.Either;

public class ParserPhase extends Phase<String, AST> {

    public ParserPhase(String filePath) {
        super(filePath);
    }

    @Override
    public Either<Exception, AST> get() {
        try {
            parser parser = new parser(new FileReader(obj)); //filepath
            AST ast = (AST) parser.parse().value;
            return Either.right(ast);
        } catch (Exception ex) {
            return Either.left(ex);
        }
    }

    public static Either<Exception, AST> apply(String filePath) {
        return new ParserPhase(filePath).get();
    }

}

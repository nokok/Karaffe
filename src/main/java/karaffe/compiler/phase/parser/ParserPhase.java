/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import karaffe.compiler.phase.Phase;
import karaffe.core.Either;

public class ParserPhase extends Phase<String, ASTCompileUnit> {

    public ParserPhase(String filePath) {
        super(filePath);
    }

    @Override
    public Either<Exception, ASTCompileUnit> get() {
        try {
            Parser parser = new Parser(new FileReader(new File(obj)));
            return Either.right(parser.CompileUnit());
        } catch (FileNotFoundException | ParseException ex) {
            return Either.left(ex);
        }
    }

    public static Either<Exception, ASTCompileUnit> apply(String filePath) {
        return new ParserPhase(filePath).get();
    }

}

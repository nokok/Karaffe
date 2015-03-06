/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.resolvers;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.phase.parser.ASTCompileUnit;
import karaffe.core.Either;

public class ResolvePhase extends Phase<ASTCompileUnit, ASTCompileUnit> {

    public ResolvePhase(ASTCompileUnit obj) {
        super(obj);
    }

    @Override
    public Either<Exception, ASTCompileUnit> get() {
        return Either.right(obj);
    }

    public static Either<Exception, ASTCompileUnit> apply(ASTCompileUnit compileUnit) {
        return new ResolvePhase(compileUnit).get();
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.resolvers;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;
import karaffe.core.Either;

public class ResolvePhase extends Phase<AST, AST> {

    public ResolvePhase(AST obj) {
        super(obj);
    }

    @Override
    public Either<Exception, AST> get() {
        return Either.right(obj);
    }

    public static Either<Exception, AST> apply(AST compileUnit) {
        return new ResolvePhase(compileUnit).get();
    }

}

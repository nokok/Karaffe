/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.scope;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;
import karaffe.core.Either;

public class ScopePhase extends Phase<AST, AST> {

    public ScopePhase(AST obj) {
        super(obj);
    }

    @Override
    public Either<Exception, AST> get() {
        return Either.right(obj);
    }

    public static Either<Exception, AST> apply(AST compileUnit) {
        return new ScopePhase(compileUnit).get();
    }

}

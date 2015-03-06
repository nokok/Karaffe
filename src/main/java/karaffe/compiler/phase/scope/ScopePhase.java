/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.scope;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.phase.parser.ASTCompileUnit;
import karaffe.core.Either;

public class ScopePhase extends Phase<ASTCompileUnit, ASTCompileUnit> {

    public ScopePhase(ASTCompileUnit obj) {
        super(obj);
    }

    @Override
    public Either<Exception, ASTCompileUnit> get() {
        obj.dump("");
        return Either.right(obj);
    }

    public static Either<Exception, ASTCompileUnit> apply(ASTCompileUnit compileUnit) {
        return new ScopePhase(compileUnit).get();
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.resolvers;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;

public class ResolvePhase extends Phase<AST, AST> {

    public ResolvePhase() {
        super("resolver");
    }

    @Override
    public AST apply(AST t) {
        return t;
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.checker;

import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;

public class PackagePathChecker extends Phase<AST, AST> {

    @Override
    public AST apply(AST t) {
        return t;
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.visitor;

import karaffe.compiler.tree.PackageDecl;

public interface Visitor {

    public void packageDecl(PackageDecl packageDecl);
}

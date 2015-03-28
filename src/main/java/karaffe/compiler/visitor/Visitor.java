/**
 * Karaffe Programming Language
 */
package karaffe.compiler.visitor;

import karaffe.compiler.tree.AmbiguousName;
import karaffe.compiler.tree.CompileUnit;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.tree.ImportDecl;
import karaffe.compiler.tree.PackageDecl;

public interface Visitor {

    public void packageDecl(PackageDecl packageDecl);

    public void identifier(Identifier aThis);

    public void compileUnit(CompileUnit aThis);

    public void ambiguousName(AmbiguousName aThis);

    public void errorNode(ErrorNode aThis);

    public void importDecl(ImportDecl aThis);
}

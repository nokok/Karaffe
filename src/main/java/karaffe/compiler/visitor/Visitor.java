/**
 * Karaffe Programming Language
 */
package karaffe.compiler.visitor;

import karaffe.compiler.tree.imports.AliasImport;
import karaffe.compiler.tree.AmbiguousName;
import karaffe.compiler.tree.imports.BlockImport;
import karaffe.compiler.tree.imports.BlockImportBodyList;
import karaffe.compiler.tree.CompileUnit;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.tree.imports.GroupImport;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.tree.imports.IdentifierList;
import karaffe.compiler.tree.imports.ImportDecl;
import karaffe.compiler.tree.imports.ImportDeclList;
import karaffe.compiler.tree.PackageDecl;
import karaffe.compiler.tree.imports.SimpleImport;

public interface Visitor {

    public void packageDecl(PackageDecl packageDecl);

    public void identifier(Identifier aThis);

    public void compileUnit(CompileUnit aThis);

    public void ambiguousName(AmbiguousName aThis);

    public void errorNode(ErrorNode aThis);

    public void simpleImportDecl(SimpleImport aThis);

    public void aliasImport(AliasImport aThis);

    public void importDeclList(ImportDeclList aThis);

    public void importDecl(ImportDecl aThis);

    public void groupImport(GroupImport aThis);

    public void identifierList(IdentifierList aThis);

    public void blockImport(BlockImport aThis);

    public void blockImportBodyList(BlockImportBodyList aThis);
}

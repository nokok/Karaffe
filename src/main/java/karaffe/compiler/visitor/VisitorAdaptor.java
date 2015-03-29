/**
 * Karaffe Programming Language
 */
package karaffe.compiler.visitor;

import karaffe.compiler.tree.AmbiguousName;
import karaffe.compiler.tree.compileunits.CompileUnit;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.tree.compileunits.FileNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.tree.compileunits.PackageDecl;
import karaffe.compiler.tree.imports.AliasImport;
import karaffe.compiler.tree.imports.BlockImport;
import karaffe.compiler.tree.imports.BlockImportBodyList;
import karaffe.compiler.tree.imports.GroupImport;
import karaffe.compiler.tree.imports.IdentifierList;
import karaffe.compiler.tree.imports.ImportDecl;
import karaffe.compiler.tree.imports.ImportDeclList;
import karaffe.compiler.tree.imports.SimpleImport;

public class VisitorAdaptor implements Visitor {

    @Override
    public void packageDecl(PackageDecl packageDecl) {
    }

    @Override
    public void identifier(Identifier aThis) {
    }

    @Override
    public void compileUnit(CompileUnit aThis) {
    }

    @Override
    public void ambiguousName(AmbiguousName aThis) {
    }

    @Override
    public void errorNode(ErrorNode aThis) {
    }

    @Override
    public void simpleImportDecl(SimpleImport aThis) {
    }

    @Override
    public void aliasImport(AliasImport aThis) {
    }

    @Override
    public void importDeclList(ImportDeclList aThis) {
    }

    @Override
    public void importDecl(ImportDecl aThis) {
    }

    @Override
    public void groupImport(GroupImport aThis) {
    }

    @Override
    public void identifierList(IdentifierList aThis) {
    }

    @Override
    public void blockImport(BlockImport aThis) {
    }

    @Override
    public void blockImportBodyList(BlockImportBodyList aThis) {
    }

    @Override
    public void fileNode(FileNode aThis) {
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.visitor;

import karaffe.compiler.tree.Annotation;
import karaffe.compiler.tree.AnnotationList;
import karaffe.compiler.tree.ErrorNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.tree.classdecls.AutoDecl;
import karaffe.compiler.tree.classdecls.AutoDeclBlock;
import karaffe.compiler.tree.classdecls.AutoDeclList;
import karaffe.compiler.tree.classdecls.ClassBody;
import karaffe.compiler.tree.classdecls.ClassDecl;
import karaffe.compiler.tree.classdecls.ClassDeclList;
import karaffe.compiler.tree.classdecls.ExtendsOrImplements;
import karaffe.compiler.tree.classdecls.FieldDecl;
import karaffe.compiler.tree.classdecls.SimpleClassDecl;
import karaffe.compiler.tree.classdecls.Initializer;
import karaffe.compiler.tree.compileunits.CompileUnit;
import karaffe.compiler.tree.compileunits.FileNode;
import karaffe.compiler.tree.compileunits.PackageDecl;
import karaffe.compiler.tree.expr.Expr;
import karaffe.compiler.tree.imports.AliasImport;
import karaffe.compiler.tree.imports.BlockImport;
import karaffe.compiler.tree.imports.BlockImportBodyList;
import karaffe.compiler.tree.imports.GroupImport;
import karaffe.compiler.tree.imports.IdentifierList;
import karaffe.compiler.tree.imports.ImportDecl;
import karaffe.compiler.tree.imports.ImportDeclList;
import karaffe.compiler.tree.imports.SimpleImport;
import karaffe.compiler.tree.modifiers.AbstractModifier;
import karaffe.compiler.tree.modifiers.FinalModifier;
import karaffe.compiler.tree.modifiers.ModifierList;
import karaffe.compiler.tree.modifiers.NullableModifier;
import karaffe.compiler.tree.modifiers.PrivateModifier;
import karaffe.compiler.tree.modifiers.PublicModifier;
import karaffe.compiler.tree.modifiers.StaticModifier;
import karaffe.compiler.tree.name.AmbiguousName;
import karaffe.compiler.tree.name.PackageOrTypeName;
import karaffe.compiler.tree.type.FunctionType;
import karaffe.compiler.tree.type.ParameterizedType;
import karaffe.compiler.tree.type.SimpleType;
import karaffe.compiler.tree.type.SimpleTypeList;
import karaffe.compiler.tree.type.Type;
import karaffe.compiler.tree.type.TypeName;
import karaffe.compiler.tree.type.TypeParameter;

public class VisitorAdaptor implements Visitor {

    @Override
    public void packageDecl(PackageDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void identifier(Identifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void compileUnit(CompileUnit aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void ambiguousName(AmbiguousName aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void errorNode(ErrorNode aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void simpleImportDecl(SimpleImport aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void aliasImport(AliasImport aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void importDeclList(ImportDeclList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void importDecl(ImportDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void groupImport(GroupImport aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void identifierList(IdentifierList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void blockImport(BlockImport aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void blockImportBodyList(BlockImportBodyList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void fileNode(FileNode aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void staticModifier(StaticModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void nullableModifier(NullableModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void autoDeclBlock(AutoDeclBlock aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void autoDecl(AutoDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void autoDeclList(AutoDeclList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void classBody(ClassBody aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void classDecl(ClassDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void classDeclList(ClassDeclList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void fieldDecl(FieldDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void simpleClassDecl(SimpleClassDecl aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void initializer(Initializer aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void simpleType(SimpleType aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void functionType(FunctionType aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void type(Type aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void parameterizedType(ParameterizedType aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void typeParameter(TypeParameter aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void simpleTypeList(SimpleTypeList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void expr(Expr aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void annotationList(AnnotationList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void annotation(Annotation aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void abstractModifier(AbstractModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void finalModifier(FinalModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void modifierList(ModifierList aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void privateModifier(PrivateModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void publicModifier(PublicModifier aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void typeName(TypeName aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void extendsOrImplements(ExtendsOrImplements aThis) {
        aThis.childrenAccept(this);
    }

    @Override
    public void packageOrTypeName(PackageOrTypeName aThis) {
        aThis.childrenAccept(this);
    }

}

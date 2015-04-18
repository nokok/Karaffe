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
import karaffe.compiler.tree.classdecls.fields.Initializer;
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

    public void fileNode(FileNode aThis);

    public void staticModifier(StaticModifier aThis);

    public void nullableModifier(NullableModifier aThis);

    public void autoDeclBlock(AutoDeclBlock aThis);

    public void autoDecl(AutoDecl aThis);

    public void autoDeclList(AutoDeclList aThis);

    public void classBody(ClassBody aThis);

    public void classDecl(ClassDecl aThis);

    public void classDeclList(ClassDeclList aThis);

    public void fieldDecl(FieldDecl aThis);

    public void simpleClassDecl(SimpleClassDecl aThis);

    public void initializer(Initializer aThis);

    public void simpleType(SimpleType aThis);

    public void functionType(FunctionType aThis);

    public void type(Type aThis);

    public void parameterizedType(ParameterizedType aThis);

    public void typeParameter(TypeParameter aThis);

    public void simpleTypeList(SimpleTypeList aThis);

    public void expr(Expr aThis);

    public void annotationList(AnnotationList aThis);

    public void annotation(Annotation aThis);

    public void abstractModifier(AbstractModifier aThis);

    public void finalModifier(FinalModifier aThis);

    public void modifierList(ModifierList aThis);

    public void privateModifier(PrivateModifier aThis);

    public void publicModifier(PublicModifier aThis);

    public void typeName(TypeName aThis);

    public void extendsOrImplements(ExtendsOrImplements aThis);

    public void packageOrTypeName(PackageOrTypeName aThis);
}

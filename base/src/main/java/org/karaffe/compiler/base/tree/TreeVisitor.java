package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.ArrayType;
import org.karaffe.compiler.base.tree.type.PrimitiveType;
import org.karaffe.compiler.base.tree.type.SimpleType;
import org.karaffe.compiler.base.tree.type.Type;

public interface TreeVisitor<R, P> {

    R visitLetDef(Def simpleDef, P p);

    R visitAssignmentDef(Def simpleDef, P p);

    R visitClassDef(Def def, P p);

    R visitSimpleImportDef(Def def, P p);

    R visitOnDemandImportDef(Def onDemandImport, P p);

    R visitMethodDef(Def def, P p);

    R visitPackageDef(Def def, P p);

    R visitNoneName(Name name, P p);

    R visitFQCN(Name name, P p);

    R visitPackageName(Name name, P p);

    R visitThisName(Name name, P p);

    R visitVarName(Name name, P p);

    R visitTypeName(Name name, P p);

    R visit(Modifier modifier, P p);

    R visitStaticMod(Modifier modifier, P p);

    R visitPublicMod(Modifier modifier, P p);

    R visitRefType(SimpleType type, P p);

    R visitArrayType(ArrayType type, P p);

    R visitPrimitiveIntType(PrimitiveType type, P p);

    R visitPrimitiveCharType(PrimitiveType type, P p);

    R visitPrimitiveByteType(PrimitiveType type, P p);

    R visitVoidType(PrimitiveType type, P p);

    R visitPrimitiveBooleanType(PrimitiveType type, P p);

    R visitPrimitiveLongType(PrimitiveType type, P p);

    R visitPrimitiveFloatType(PrimitiveType type, P p);

    R visitPrimitiveShortType(PrimitiveType type, P p);

    R visitPrimitiveDoubleType(PrimitiveType type, P p);

    R visitNoType(Type type, P p);

    R visitCompileUnit(Tree.CompilationUnit tree, P p);

    R visitTemplate(Tree.Template template, P p);

    R visitApply(Apply apply, P p);

    R visitAtom(Atom atom, P p);

    R visitBlock(Block block, P p);

    R visitSelect(Select select, P p);

    R visitOperator(Operator operator, P p);

    R visitTuple(Tuple tuple, P p);

    R visitIfExpr(IfExpr ifExpr, P p);

    R visitWhileExpr(WhileExpr whileExpr, P p);

    R visitEmpty(EmptyTree emptyTree, P p);

    R visitCast(Cast cast, P p);

}

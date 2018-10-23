package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.Identifier;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.IntegerLiteral;
import org.karaffe.compiler.base.tree.expr.StringLiteral;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.expr.op.And;
import org.karaffe.compiler.base.tree.expr.op.Bang;
import org.karaffe.compiler.base.tree.expr.op.Comma;
import org.karaffe.compiler.base.tree.expr.op.DeepEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.DeepNotEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.Div;
import org.karaffe.compiler.base.tree.expr.op.EqualsTo;
import org.karaffe.compiler.base.tree.expr.op.GreaterThan;
import org.karaffe.compiler.base.tree.expr.op.GreaterThanEquals;
import org.karaffe.compiler.base.tree.expr.op.LessThan;
import org.karaffe.compiler.base.tree.expr.op.LessThanEquals;
import org.karaffe.compiler.base.tree.expr.op.Minus;
import org.karaffe.compiler.base.tree.expr.op.Mod;
import org.karaffe.compiler.base.tree.expr.op.Mul;
import org.karaffe.compiler.base.tree.expr.op.NotEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.Or;
import org.karaffe.compiler.base.tree.expr.op.Plus;
import org.karaffe.compiler.base.tree.expr.op.Pow;
import org.karaffe.compiler.base.tree.expr.op.Range;
import org.karaffe.compiler.base.tree.modifier.PublicModifier;
import org.karaffe.compiler.base.tree.modifier.StaticModifier;
import org.karaffe.compiler.base.tree.modifier.SyntheticModifier;
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.InternalName;
import org.karaffe.compiler.base.tree.term.TypeName;
import org.karaffe.compiler.base.tree.term.VarName;
import org.karaffe.compiler.base.tree.type.Array;
import org.karaffe.compiler.base.tree.type.primitive.Void;

public interface TreeVisitor<R, P> {

    R visit(LetDef simpleDef, P p);

    R visit(AssignmentDef simpleDef, P p);

    R visit(ClassDef def, P p);

    R visit(SimpleImport def, P p);

    R visit(OnDemandImport onDemandImport, P p);

    R visit(MethodDef def, P p);

    R visit(PackageDef def, P p);

    R visit(Tree.CompilationUnit tree, P p);

    R visit(Tree.Template template, P p);

    R visit(Apply apply, P p);

    R visit(Block block, P p);

    R visit(Tuple tuple, P p);

    R visit(IfExpr ifExpr, P p);

    R visit(WhileExpr whileExpr, P p);

    R visit(EmptyTree emptyTree, P p);

    R visit(Cast cast, P p);

    R visit(Binding binding, P p);

    R visit(ReturnStatement returnStatement, P p);

    R visit(PublicModifier publicModifier, P p);

    R visit(StaticModifier staticModifier, P p);

    R visit(SyntheticModifier syntheticModifier, P p);

    R visit(StringLiteral stringLiteral, P p);

    R visit(IntegerLiteral integerLiteral, P p);

    R visit(Identifier identifier, P p);

    R visit(Range range, P p);

    R visit(Comma comma, P p);

    R visit(Or or, P p);

    R visit(NotEqualsTo notEqualsTo, P p);

    R visit(Mul mul, P p);

    R visit(Mod mod, P p);

    R visit(Minus minus, P p);

    R visit(And and, P p);

    R visit(Bang bang, P p);

    R visit(DeepEqualsTo deepEqualsTo, P p);

    R visit(DeepNotEqualsTo deepNotEqualsTo, P p);

    R visit(Div div, P p);

    R visit(EqualsTo equalsTo, P p);

    R visit(GreaterThan greaterThan, P p);

    R visit(GreaterThanEquals greaterThanEquals, P p);

    R visit(LessThanEquals lessThanEquals, P p);

    R visit(LessThan lessThan, P p);

    R visit(Plus plus, P p);

    R visit(Pow pow, P p);

    R visit(TypeName typeName, P p);

    R visit(VarName varName, P p);

    R visit(Array array, P p);

    R visit(Void aVoid, P p);

    R visit(InternalName internalName, P p);
}

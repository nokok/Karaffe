package org.karaffe.compiler.base.util;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MapVisitor implements TreeVisitor<Map<String, Object>, Void> {

    private Map<String, Object> visitTree(Tree tree) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
        map.put("pos", tree.getPos());
        map.put("name", tree.getName().accept(this, null));
        map.put("type", tree.getTypeName().accept(this, null));
        map.put("body", visitChildren(tree));
        return map;
    }

    private List<Map<String, Object>> visitChildren(Tree tree) {
        return tree.getChildren().stream().map(t -> t.accept(this, null)).collect(toList());
    }

    @Override
    public Map<String, Object> visit(Tree.CompilationUnit tree, Void aVoid) {
        return visitTree(tree);
    }

    @Override
    public Map<String, Object> visit(Tree.Template template, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", template.getName());
        map.put("pos", template.getPos());
        map.put("superClass", template.getSuperClass().accept(this, null));
        map.put("interfaces", template.getInterfaces().stream().map(t -> t.accept(this, null)).collect(toList()));
        map.put("body", visitChildren(template));
        return map;
    }

    @Override
    public Map<String, Object> visit(Apply apply, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("target", apply.getChildren().get(0).accept(this, null));
        map.put("methodName", apply.getName().accept(this, null));
        map.put("args", apply.getChildren().stream().skip(1).map(t -> t.accept(this, null)).collect(toList()));
        return map;
    }

    @Override
    public Map<String, Object> visit(Block block, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("body", visitChildren(block));
        return map;
    }

    @Override
    public Map<String, Object> visit(Tuple tuple, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("body", visitChildren(tuple));
        return map;
    }

    @Override
    public Map<String, Object> visit(IfExpr ifExpr, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("cond", ifExpr.getChildren().get(0).accept(this, null));
        map.put("then", ifExpr.getChildren().get(1).accept(this, null));
        map.put("else", ifExpr.getChildren().size() == 3 ? ifExpr.getChildren().get(2).accept(this, null) : "");
        return map;
    }

    @Override
    public Map<String, Object> visit(WhileExpr whileExpr, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("cond", whileExpr.getChildren().get(0).accept(this, null));
        map.put("body", whileExpr.getChildren().get(1).accept(this, null));
        return map;
    }

    @Override
    public Map<String, Object> visit(EmptyTree emptyTree, Void aVoid) {
        return Collections.singletonMap("empty", "");
    }

    @Override
    public Map<String, Object> visit(Cast cast, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("expr", cast.getChildren().get(0).accept(this, null));
        map.put("target", cast.getChildren().get(1).accept(this, null));
        return map;
    }

    @Override
    public Map<String, Object> visit(Binding binding, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", binding.getName().accept(this, null));
        map.put("type", binding.getTypeName().accept(this, null));
        return map;
    }

    @Override
    public Map<String, Object> visit(ReturnStatement returnStatement, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("body", visitChildren(returnStatement));
        return map;
    }

    @Override
    public Map<String, Object> visit(PublicModifier publicModifier, Void aVoid) {
        return Collections.singletonMap("mod", "public");
    }

    @Override
    public Map<String, Object> visit(StaticModifier staticModifier, Void aVoid) {
        return Collections.singletonMap("mod", "static");
    }

    @Override
    public Map<String, Object> visit(SyntheticModifier syntheticModifier, Void aVoid) {
        return Collections.singletonMap("mod", "synthetic");
    }

    @Override
    public Map<String, Object> visit(StringLiteral stringLiteral, Void aVoid) {
        return Collections.singletonMap("value", stringLiteral.getValue());
    }

    @Override
    public Map<String, Object> visit(IntegerLiteral integerLiteral, Void aVoid) {
        return Collections.singletonMap("value", integerLiteral.getValue());
    }

    @Override
    public Map<String, Object> visit(Identifier identifier, Void aVoid) {
        return Collections.singletonMap("value", identifier.getName().getName());

    }

    @Override
    public Map<String, Object> visit(Range range, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Comma comma, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Or or, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(NotEqualsTo notEqualsTo, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Mul mul, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Mod mod, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Minus minus, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(And and, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Bang bang, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(DeepEqualsTo deepEqualsTo, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(DeepNotEqualsTo deepNotEqualsTo, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Div div, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(EqualsTo equalsTo, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(GreaterThan greaterThan, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(GreaterThanEquals greaterThanEquals, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(LessThanEquals lessThanEquals, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(LessThan lessThan, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Plus plus, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Pow pow, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(TypeName typeName, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(VarName varName, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Array array, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(Void aVoid, Void aVoid2) {
        return null;
    }

    @Override
    public Map<String, Object> visit(InternalName internalName, Void aVoid) {
        return null;
    }

    @Override
    public Map<String, Object> visit(LetDef simpleDef, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", simpleDef.getName());
        map.put("type", simpleDef.getTypeName().accept(this, null));
        map.put("expr", visitChildren(simpleDef));
        return map;
    }

    @Override
    public Map<String, Object> visit(AssignmentDef simpleDef, Void aVoid) {
        return visitTree(simpleDef);
    }

    @Override
    public Map<String, Object> visit(ClassDef def, Void aVoid) {
        return visitTree(def);
    }

    @Override
    public Map<String, Object> visit(SimpleImport tree, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
        map.put("pos", tree.getPos());
        map.put("importPath", tree.getName().accept(this, null));
        map.put("body", visitChildren(tree));
        return map;
    }

    @Override
    public Map<String, Object> visit(OnDemandImport tree, Void aVoid) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
        map.put("pos", tree.getPos());
        map.put("importPackage", tree.getName().accept(this, null));
        map.put("body", visitChildren(tree));
        return map;
    }

    @Override
    public Map<String, Object> visit(MethodDef def, Void aVoid) {
        return visitTree(def);
    }

    @Override
    public Map<String, Object> visit(PackageDef def, Void aVoid) {
        return visitTree(def);
    }

}

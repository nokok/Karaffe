package org.karaffe.compiler.base.tree.def;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifiers;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

public interface Defs {

    static Def packageDef(Position position, String packageName) {
        return packageDef(position, null, packageName);
    }

    static Def packageDef(Position position, Tree parent, TerminalNode packageName) {
        AbstractDef packageDef = new PackageDef(parent);
        packageDef.setPos(position);
        Path name = Terms.packageName(Position.of(packageName.getSymbol()), packageName.getText());
        packageDef.setName(name);
        return packageDef;
    }

    static Def packageDef(Position position, Tree parent, String packageName) {
        AbstractDef packageDef = new PackageDef(parent);
        packageDef.setPos(position);
        Path name = Terms.packageName(Position.noPos(), packageName);
        packageDef.setName(name);
        return packageDef;
    }

    static Def importDef(Position position, String importName) {
        return importDef(position, null, importName);
    }

    static Def importDef(Position position, Tree parent, String importName) {
        AbstractDef importDef = new SimpleImport(parent);
        importDef.setPos(position);
        importDef.setName(Terms.typeName(importName));
        return importDef;
    }

    static Def onDemandImportDef(Position position, String packageName) {
        return onDemandImportDef(position, null, packageName);
    }

    static Def onDemandImportDef(Position position, Tree parent, String packageName) {
        AbstractDef importDef = new OnDemandImport(parent);
        importDef.setPos(position);
        Path pkgName = Terms.packageName(Position.noPos(), packageName);
        importDef.setName(pkgName);
        return importDef;
    }

    static Def classDef(Position position, String className) {
        return classDef(position, null, className);
    }

    static Def classDef(Position position, TerminalNode className) {
        AbstractDef classDef = new ClassDef();
        classDef.setPos(position);
        classDef.setName(Terms.typeName(Position.of(className.getSymbol()), className.getText()));
        return classDef;
    }

    static Def classDef(Position position, Tree parent, String className) {
        AbstractDef classDef = new ClassDef(parent);
        classDef.setPos(position);
        classDef.setName(Terms.typeName(className));
        return classDef;
    }

    static Def methodDef(Position position, Tree parent, String methodName, Path returnTypeName, Tuple parameters) {
        AbstractDef methodDef = new MethodDef(parent);
        methodDef.setPos(position);
        methodDef.setName(Terms.varName(Position.noPos(), methodName));
        methodDef.setTypeName(returnTypeName);
        methodDef.setOrReplaceChild(0, parameters);
        return methodDef;
    }

    static Def mainMethodDef(Position position) {
        return mainMethodDef(position, null);
    }

    static Def mainMethodDef(Position position, Tree parent) {
        Tuple params = Exprs.tuple();
        Binding binding = new Binding(parent);
        binding.setName(Terms.varName(Position.noPos(), "args"));
        binding.setTypeName(Terms.arrayTypeName(Position.noPos(), Terms.typeName(Position.noPos(), "String")));
        params.addChild(binding);
        Def methodDef = methodDef(
                position,
                parent,
                "main",
                Terms.primitiveVoid(Position.noPos()),
                params
        );
        params.setParent(methodDef);
        methodDef.addModifier(Modifiers.modPublic(methodDef));
        methodDef.addModifier(Modifiers.modStatic(methodDef));
        return methodDef;
    }

    static Def letDef(Position position, Path letName, Path typeName, Tree initializer) {
        AbstractDef letDef = new LetDef();
        letDef.setPos(position);
        letDef.setName(letName);
        letDef.setTypeName(typeName);
        letDef.addChild(initializer);
        return letDef;
    }

    static Def assignment(Position position, Path reassignName, Tree expr) {
        AbstractDef assignment = new AssignmentDef();
        assignment.setPos(position);
        assignment.setName(reassignName);
        assignment.addChild(expr);
        return assignment;
    }
}

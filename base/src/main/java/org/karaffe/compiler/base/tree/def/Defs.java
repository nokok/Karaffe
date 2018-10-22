package org.karaffe.compiler.base.tree.def;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifiers;
import org.karaffe.compiler.base.tree.term.Terms;

public interface Defs {

    static Def packageDef(Position position, String packageName) {
        AbstractDef packageDef = new PackageDef();
        packageDef.setPos(position);
        Tree name = Terms.packageName(Position.noPos(), packageName);
        packageDef.setName(name);
        return packageDef;
    }

    static Def importDef(Position position, String importName) {
        AbstractDef importDef = new SimpleImport();
        importDef.setPos(position);
        importDef.setName(Terms.typeName(importName));
        return importDef;
    }

    static Def onDemandImportDef(Position position, String packageName) {
        AbstractDef importDef = new OnDemandImport();
        importDef.setPos(position);
        Tree pkgName = Terms.packageName(Position.noPos(), packageName);
        importDef.setName(pkgName);
        return importDef;
    }

    static Def classDef(Position position, TerminalNode className) {
        AbstractDef classDef = new ClassDef();
        classDef.setPos(position);
        classDef.setName(Terms.typeName(Position.of(className.getSymbol()), className.getText()));
        return classDef;
    }

    static Def classDef(Position position, String className) {
        AbstractDef classDef = new ClassDef();
        classDef.setPos(position);
        classDef.setName(Terms.typeName(className));
        return classDef;
    }

    static Def methodDef(Position position, String methodName, Tree returnTypeName, Tuple parameters) {
        AbstractDef methodDef = new MethodDef();
        methodDef.setPos(position);
        methodDef.setName(Terms.varName(position, methodName));
        methodDef.setTypeName(returnTypeName);
        methodDef.setOrReplaceChild(0, parameters);
        return methodDef;
    }

    static Def mainMethodDef(Position position) {
        return mainMethodDef(position, null);
    }

    static Def mainMethodDef(Position position, Tree parent) {
        Tuple params = Exprs.tuple();
        Binding binding = new Binding();
        binding.setName(Terms.varName(Position.noPos(), "args"));
        binding.setTypeName(Terms.arrayTypeName(Position.noPos(), Terms.typeName(Position.noPos(), "String")));
        params.addChild(binding);
        Def methodDef = methodDef(
                position,
                "main",
                Terms.primitiveVoid(Position.noPos()),
                params
        );
        methodDef.addModifier(Modifiers.modPublic(methodDef.getPos()));
        methodDef.addModifier(Modifiers.modStatic(methodDef.getPos()));
        return methodDef;
    }

    static Def letDef(Position position, Tree letName, Tree typeName, Tree initializer) {
        AbstractDef letDef = new LetDef();
        letDef.setPos(position);
        letDef.setName(letName);
        letDef.setTypeName(typeName);
        letDef.addChild(initializer);
        return letDef;
    }

    static Def assignment(Position position, Tree reassignName, Tree expr) {
        AbstractDef assignment = new AssignmentDef();
        assignment.setPos(position);
        assignment.setName(reassignName);
        assignment.addChild(expr);
        return assignment;
    }
}

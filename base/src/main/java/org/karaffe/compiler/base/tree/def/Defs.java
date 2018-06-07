package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifiers;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.term.Terms;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.tree.type.Types;

public interface Defs {

    static Def packageDef(String packageName) {
        return packageDef(null, packageName);
    }

    static Def packageDef(Tree parent, String packageName) {
        SimpleDef packageDef = new SimpleDef(parent, DefKind.PACKAGE);
        packageDef.setName(Terms.packageName(packageName));
        return packageDef;
    }

    static Def importDef(String importName) {
        return importDef(null, importName);
    }

    static Def importDef(Tree parent, String importName) {
        SimpleDef importDef = new SimpleDef(parent, DefKind.SIMPLE_IMPORT);
        importDef.setName(Terms.fqcn(importName));
        return importDef;
    }

    static Def classDef(String className) {
        return classDef(null, className);
    }

    static Def classDef(Tree parent, String className) {
        SimpleDef classDef = new SimpleDef(parent, DefKind.CLASS);
        classDef.setName(Terms.typeName(className));
        return classDef;
    }

    static Def methodDef(Tree parent, String methodName, Type returnType, Tuple parameters) {
        SimpleDef methodDef = new SimpleDef(parent, DefKind.METHOD);
        methodDef.setType(returnType);
        methodDef.setOrReplaceChild(0, parameters);
        methodDef.setName(Terms.varName(methodName));
        return methodDef;
    }

    static Def mainMethodDef() {
        return mainMethodDef(null);
    }

    static Def mainMethodDef(Tree parent) {
        Tuple params = Exprs.tuple();
        params.addChild(Types.array(Types.simple(Terms.typeName("String"))));
        Def methodDef = methodDef(
                parent,
                "main",
                Types.jvoid(),
                params
        );
        params.setParent(methodDef);
        methodDef.addModifier(Modifiers.modPublic(methodDef));
        methodDef.addModifier(Modifiers.modStatic(methodDef));
        methodDef.setType(Types.jvoid());
        return methodDef;
    }

    static Def letDef(Name letName, Name typeName, Tree initializer) {
        SimpleDef letDef = new SimpleDef(DefKind.LET);
        letDef.setName(letName);
        letDef.setType(typeName.asType());
        letDef.addChild(initializer);
        return letDef;
    }

    static Def assignment(Name reassignName, Tree expr) {
        SimpleDef assignment = new SimpleDef(DefKind.ASSIGNMENT);
        assignment.setName(reassignName);
        assignment.addChild(expr);
        return assignment;
    }
}

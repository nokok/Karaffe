package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.modifier.Modifiers;
import org.karaffe.compiler.base.tree.term.Terms;

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
        SimpleDef importDef = new SimpleDef(parent, DefKind.IMPORT);
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

    static Def methodDef(String methodName) {
        return methodDef(null, methodName);
    }

    static Def methodDef(Tree parent, String methodName) {
        SimpleDef methodDef = new SimpleDef(parent, DefKind.METHOD);
        methodDef.setName(Terms.varName(methodName));
        return methodDef;
    }

    static Def mainMethodDef() {
        return mainMethodDef(null);
    }

    static Def mainMethodDef(Tree parent) {
        Def methodDef = methodDef(parent, "main");
        methodDef.addModifier(Modifiers.modPublic(methodDef));
        methodDef.addModifier(Modifiers.modStatic(methodDef));
        return methodDef;
    }
}

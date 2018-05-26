package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.modifiers.Modifiers;
import org.karaffe.compiler.base.tree.term.Terms;

public interface Defs extends Tree {

    static PackageDef packageDef(String packageName) {
        return packageDef(null, packageName);
    }

    static PackageDef packageDef(Tree parent, String packageName) {
        PackageDef packageDef = new PackageDef(parent);
        packageDef.setName(Terms.packageName(packageName));
        return packageDef;
    }

    static ImportDef importDef(String importName) {
        return importDef(null, importName);
    }

    static ImportDef importDef(Tree parent, String importName) {
        ImportDef importDef = new ImportDef(parent);
        importDef.setName(Terms.fqcn(importName));
        return importDef;
    }

    static ClassDef classDef(String className) {
        return classDef(null, className);
    }

    static ClassDef classDef(Tree parent, String className) {
        ClassDef classDef = new ClassDef(parent);
        classDef.setName(Terms.typeName(className));
        return classDef;
    }

    static MethodDef methodDef(String methodName) {
        return methodDef(null, methodName);
    }

    static MethodDef methodDef(Tree parent, String methodName) {
        MethodDef methodDef = new MethodDef(parent);
        methodDef.setName(Terms.varName(methodName));
        return methodDef;
    }

    static MethodDef mainMethodDef() {
        return mainMethodDef(null);
    }

    static MethodDef mainMethodDef(Tree parent) {
        MethodDef methodDef = methodDef(parent, "main");
        methodDef.addModifier(Modifiers.modPublic(methodDef));
        methodDef.addModifier(Modifiers.modStatic(methodDef));
        return methodDef;
    }
}

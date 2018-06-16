package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifiers;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

public interface Defs {

    static Def packageDef(String packageName) {
        return packageDef(null, packageName);
    }

    static Def packageDef(Tree parent, String packageName) {
        AbstractDef packageDef = new PackageDef(parent);
        Path name = Terms.packageName(packageName);
        return packageDef;
    }

    static Def importDef(String importName) {
        return importDef(null, importName);
    }

    static Def importDef(Tree parent, String importName) {
        AbstractDef importDef = new SimpleImport(parent);
        importDef.setName(Terms.typeName(importName));
        return importDef;
    }

    static Def onDemandImportDef(String packageName) {
        return onDemandImportDef(null, packageName);
    }

    static Def onDemandImportDef(Tree parent, String packageName) {
        AbstractDef importDef = new OnDemandImport(parent);
        Path pkgName = Terms.packageName(packageName);
        importDef.setName(pkgName);
        return importDef;
    }

    static Def classDef(String className) {
        return classDef(null, className);
    }

    static Def classDef(Tree parent, String className) {
        AbstractDef classDef = new ClassDef(parent);
        return classDef;
    }

    static Def methodDef(Tree parent, String methodName, Path returnTypeName, Tuple parameters) {
        AbstractDef methodDef = new MethodDef(parent);
        methodDef.setName(Terms.varName(methodName));
        methodDef.setTypeName(returnTypeName);
        methodDef.setOrReplaceChild(0, parameters);
        return methodDef;
    }

    static Def mainMethodDef() {
        return mainMethodDef(null);
    }

    static Def mainMethodDef(Tree parent) {
        Tuple params = Exprs.tuple();
        Binding binding = new Binding(parent);
        binding.setName(Terms.varName("args"));
        binding.setTypeName(Terms.arrayTypeName(Terms.typeName("String")));
        params.addChild(binding);
        Def methodDef = methodDef(
                parent,
                "main",
                Terms.primitiveVoid(),
                params
        );
        params.setParent(methodDef);
        methodDef.addModifier(Modifiers.modPublic(methodDef));
        methodDef.addModifier(Modifiers.modStatic(methodDef));
        return methodDef;
    }

    static Def letDef(Path letName, Path typeName, Tree initializer) {
        AbstractDef letDef = new LetDef();
        letDef.setName(letName);
        letDef.setTypeName(typeName);
        letDef.addChild(initializer);
        return letDef;
    }

    static Def assignment(Path reassignName, Tree expr) {
        AbstractDef assignment = new AssignmentDef();
        assignment.setName(reassignName);
        assignment.addChild(expr);
        return assignment;
    }
}

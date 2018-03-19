package org.karaffe.compiler.backend.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.api.TypeDefStatement;
import org.karaffe.compiler.ast.statements.ClassDef;

public interface ClassDefTransformer extends
        TypeDefSuperClassTransformer,
        TypeDefInterfacesTransformer,
        TypeDefMemberTransformer {

    public default void onClassDefBefore(ClassDef classDef) {

    }

    public default void onClassDefAfter(ClassDef classDef) {

    }

    public default ClassDef transformBody(ClassDef oldClassDef) {
        return new ClassDef(
                oldClassDef.getPosition(),
                transform(oldClassDef.getName()),
                transform(oldClassDef.getSuperClassName()),
                oldClassDef.getInterfaceNames().stream().map(this::transform).collect(Collectors.toList()),
                oldClassDef.getBody().stream().map(this::transform).collect(Collectors.toList()));
    }

    public default TypeDefStatement transform(ClassDef oldClassDef) {
        onClassDefBefore(oldClassDef);
        ClassDef classDef = transformBody(oldClassDef);
        onClassDefAfter(classDef);
        return classDef;
    }
}

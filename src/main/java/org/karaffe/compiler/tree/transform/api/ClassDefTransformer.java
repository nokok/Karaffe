package org.karaffe.compiler.tree.transform.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.statements.ClassDef;

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

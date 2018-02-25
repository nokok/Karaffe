package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.statements.ClassDef;

public interface ClassDefTransformer extends
        TypeDefSuperClassTransformer,
        TypeDefInterfacesTransformer,
        TypeDefMemberTransformer,
        ClassDefTransformListener {

    public default TypeDefStatement transform(ClassDef oldClassDef) {
        onClassDefBefore(oldClassDef);
        ClassDef classDef = new ClassDef(
                oldClassDef.getPosition(),
                transform(oldClassDef.getName()),
                transformOnTypeDefSuperClass(oldClassDef, oldClassDef.getSuperClassName()),
                transformOnTypeDefInterfaces(oldClassDef, oldClassDef.getInterfaceNames()));
        for (TypeDefMember member : oldClassDef.getBody()) {
            classDef.addMember(transform(member));
        }
        onClassDefAfter(classDef);
        return classDef;
    }
}

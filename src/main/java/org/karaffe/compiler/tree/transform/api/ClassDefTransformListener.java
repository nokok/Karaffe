package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.statements.ClassDef;

public interface ClassDefTransformListener {

    public default void onClassDefBefore(ClassDef classDef) {

    }

    public default void onClassDefAfter(ClassDef classDef) {

    }
}

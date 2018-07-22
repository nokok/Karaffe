package org.karaffe.compiler.frontend.karaffe.subject;

import org.karaffe.compiler.base.tree.def.ClassDef;

import java.util.function.Consumer;

public interface ClassDefSubject<P> extends ToVisitor<P> {
    void onClassDef(Consumer<ClassDef> f);

    static <P> ClassDefSubject<P> getSubject() {
        return new ClassDefSubjectImpl<>();
    }
}

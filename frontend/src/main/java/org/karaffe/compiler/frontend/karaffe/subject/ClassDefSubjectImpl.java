package org.karaffe.compiler.frontend.karaffe.subject;

import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.ClassDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ClassDefSubjectImpl<P> extends DefaultVisitor<P> implements ClassDefSubject<P> {

    private List<Consumer<ClassDef>> listeners = new ArrayList<>();

    @Override
    public Tree visitClassDef(ClassDef tree, P p) {
        listeners.forEach(f -> f.accept(tree));
        return tree;
    }

    @Override
    public void onClassDef(Consumer<ClassDef> f) {
        this.listeners.add(Objects.requireNonNull(f));
    }

    @Override
    public TreeVisitor<Tree, P> asVisitor() {
        return this;
    }
}

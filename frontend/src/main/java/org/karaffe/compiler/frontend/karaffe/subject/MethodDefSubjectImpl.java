package org.karaffe.compiler.frontend.karaffe.subject;

import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.MethodDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

class MethodDefSubjectImpl<P> extends DefaultVisitor<P> implements MethodDefSubject<P> {

    private List<Object> ls = new ArrayList<>();
    private List<BiFunction<MethodDef, P, Tree>> listeners = new ArrayList<>();

    @Override
    public Tree visitMethodDef(MethodDef def, P p) {
        listeners.forEach(f -> f.apply(def, p));
        return def;
    }

    @Override
    public void onMethodDef(BiFunction<MethodDef, P, Tree> f) {
        this.ls.add(Objects.requireNonNull(f));
        this.listeners.add(Objects.requireNonNull(f));
    }

    @Override
    public TreeVisitor<Tree, P> asVisitor() {
        return this;
    }

}

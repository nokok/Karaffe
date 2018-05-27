package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.Name;

import java.util.Objects;

public abstract class AbstractType extends AbstractTree implements Type {

    private Name typeName;
    private TypeKind typeKind;

    public AbstractType(Name typeName, TypeKind typeKind) {
        this(null, typeName, typeKind);
    }

    public AbstractType(Tree parent, Name typeName, TypeKind typeKind) {
        super(parent, TreeKind.TYPE);
        this.typeName = Objects.requireNonNull(typeName);
        this.typeKind = Objects.requireNonNull(typeKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

}

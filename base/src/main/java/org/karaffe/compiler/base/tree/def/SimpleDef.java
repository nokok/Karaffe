package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.List;
import java.util.Objects;

public class SimpleDef extends AbstractTree implements Def {

    private DefKind defKind;

    public SimpleDef(DefKind defKind) {
        this(null, defKind);
    }

    public SimpleDef(Tree parent, DefKind defKind) {
        super(parent, TreeKind.DEF);
        this.defKind = Objects.requireNonNull(defKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        if (this.defKind == null) {
            throw new NullPointerException();
        }
        switch (this.defKind) {
        case LET:
            return visitor.visitLetDef(this, p);
        case ASSIGNMENT:
            return visitor.visitAssignmentDef(this, p);
        case CLASS:
            return visitor.visitClassDef(this, p);
        case SIMPLE_IMPORT:
            return visitor.visitSimpleImportDef(this, p);
        case METHOD:
            return visitor.visitMethodDef(this, p);
        case PACKAGE:
            return visitor.visitPackageDef(this, p);
        default:
            throw new IllegalStateException(this.defKind.toString());
        }
    }

    @Override
    public DefKind getDefKind() {
        return this.defKind;
    }

    @Override
    public void setDefKind(DefKind defKind) {
        this.defKind = Objects.requireNonNull(defKind);
    }

    @Override
    public void addBody(Tree body) {
        this.addChild(body);
    }

    @Override
    public List<Tree> getBody() {
        return this.getChildren();
    }
}

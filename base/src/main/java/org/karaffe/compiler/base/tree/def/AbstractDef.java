package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.List;
import java.util.Objects;

public abstract class AbstractDef extends AbstractTree implements Def {

    private DefKind defKind;

    public AbstractDef(DefKind defKind) {
        this(null, defKind);
    }

    public AbstractDef(Tree parent, DefKind defKind) {
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
            return visitor.visitLetDef((LetDef) this, p);
        case ASSIGNMENT:
            return visitor.visitAssignmentDef((AssignmentDef) this, p);
        case CLASS:
            return visitor.visitClassDef((ClassDef) this, p);
        case ONDEMAND_IMPORT:
            return visitor.visitOnDemandImportDef((OnDemandImport) this, p);
        case SIMPLE_IMPORT:
            return visitor.visitSimpleImportDef((SimpleImport) this, p);
        case METHOD:
            return visitor.visitMethodDef((MethodDef) this, p);
        case PACKAGE:
            return visitor.visitPackageDef((PackageDef) this, p);
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

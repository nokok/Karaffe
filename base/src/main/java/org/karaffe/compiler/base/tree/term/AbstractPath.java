package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.expr.Operator;

import java.util.Objects;

public abstract class AbstractPath implements Path {

    protected Position pos;
    private final String delimiterRegex;
    protected boolean isResolved;
    protected String name;
    protected String simpleName;

    protected AbstractPath(String name, String delimiterRegex) {
        this.name = Objects.requireNonNull(name);
        this.simpleName = processSimpleName(this.name, Objects.requireNonNull(delimiterRegex));
        this.delimiterRegex = delimiterRegex;
    }

    protected AbstractPath(Path other) {
        this.name = other.asFullName();
        this.simpleName = other.asSimpleName();
        this.delimiterRegex = other.delimiterRegex();
    }

    protected String processSimpleName(String fullName, String delimiterRegex) {
        String[] split = fullName.split(delimiterRegex);
        if (split.length > 1) {
            return split[split.length - 1];
        } else {
            return fullName;
        }
    }

    @Override
    public String delimiterRegex() {
        return this.delimiterRegex;
    }

    @Override
    public String asFullName() {
        return this.name;
    }

    @Override
    public String asSimpleName() {
        return this.simpleName;
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    @Override
    public void setPos(Position pos) {
        this.pos = Objects.requireNonNull(pos);
    }

    @Override
    public void markResolved() {
        this.isResolved = true;
    }

    @Override
    public void markUnResolved() {
        this.isResolved = false;
    }

    @Override
    public boolean isResolved() {
        return this.isResolved;
    }

    @Override
    public int length() {
        return this.name.length();
    }

    @Override
    public char charAt(int i) {
        return this.name.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.name.subSequence(i, i1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPath that = (AbstractPath) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(simpleName, that.simpleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, simpleName);
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        switch (this.getNameKind()) {
        case TYPENAME:
            return visitor.visitTypeName(this, p);
        case MODULE:
            return visitor.visitModuleName(this, p);
        case OPERATOR:
            return visitor.visitOperator((Operator) this, p);
        case PACKAGE:
            return visitor.visitPackageName(this, p);
        case VARNAME:
            return visitor.visitVarName(this, p);
        case THIS:
            return visitor.visitThisName(this, p);
        case NESTED:
            return visitor.visitNestedName((NestedPath) this, p);
        case VOID:
            return visitor.visitVoidType(this, p);
        default:
            throw new IllegalStateException(this.getNameKind().toString());
        }
    }

    @Override
    public String toString() {
        return this.asFullName();
    }
}

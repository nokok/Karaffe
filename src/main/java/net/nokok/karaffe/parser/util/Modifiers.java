/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

public class Modifiers {

    private final boolean isAbstract;
    private final boolean isLazy;
    private final boolean isOp;
    private final boolean isOverride;
    private final boolean isPrivate;
    private final boolean isSealed;
    private final boolean isUnaryOp;
    private final boolean isVar;
    private final ModifierType type;

    public Modifiers(
            boolean isAbstract,
            boolean isLazy,
            boolean isOp,
            boolean isOverride,
            boolean isPrivate,
            boolean isSealed,
            boolean isUnaryOp,
            boolean isVar,
            ModifierType type) {
        this.isAbstract = isAbstract;
        this.isLazy = isLazy;
        this.isOp = isOp;
        this.isOverride = isOverride;
        this.isPrivate = isPrivate;
        this.isSealed = isSealed;
        this.isUnaryOp = isUnaryOp;
        this.isVar = isVar;
        this.type = type;

    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public boolean isOp() {
        return isOp;
    }

    public boolean isOverride() {
        return isOverride;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isSealed() {
        return isSealed;
    }

    public boolean isUnaryOp() {
        return isUnaryOp;
    }

    public boolean isVar() {
        return isVar;
    }

    public ModifierType getType() {
        return type;
    }

    public static enum ModifierType {

        FUNCTION,
        OPERATOR,
        VARIABLE,
        TYPE,;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isAbstract) {
            sb.append("abstract ");
        }
        if (isLazy) {
            sb.append("lazy ");
        }
        if (isOp) {
            sb.append("op ");
        }
        if (isOverride) {
            sb.append("override ");
        }
        if (isPrivate) {
            sb.append("private ");
        }
        if (isSealed) {
            sb.append("sealed ");
        }
        if (isUnaryOp) {
            sb.append("unaryop ");
        }
        if (isVar) {
            sb.append("var ");
        }
        return sb.toString();
    }
}

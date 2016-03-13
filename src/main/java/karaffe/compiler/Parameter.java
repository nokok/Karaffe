package karaffe.compiler;

import java.util.Objects;

class Parameter {

    private final Identifier id;
    private final TypeElement type;
    private MethodDef parent;
    private String path;

    Parameter(Identifier id, TypeElement type) {
        this.id = id;
        this.type = type;
        Context.INSTANCE.add(this);
    }

    public String id() {
        return id.id();
    }

    public TypeElement type() {
        return type;
    }

    @Override
    public String toString() {
        return "(parameter " + id + " " + type + ")";
    }

    public void setParent(MethodDef methodDef) {
        if ( this.parent != null ) {

        }
        this.parent = Objects.requireNonNull(methodDef);
    }

    public MethodDef getParent() {
        return parent;
    }

    public String getPath() {
        return path;
    }

    public TypeElement getTypeElement() {
        return type;
    }

    public Expression getExpr() {
        return Expression.UNINITIALIZED;
    }

    public void setPath(String path) {
        if ( this.path != null ) {
            throw new IllegalStateException(id());
        }
        this.path = Objects.requireNonNull(path);
    }

    public Position position() {
        return new Position(id.getLine(), id.getColumn());
    }
}

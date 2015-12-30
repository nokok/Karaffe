package karaffe.compiler;

import java.util.Objects;
class Parameter {

    private final Identifier id;
    private final TypeElement type;
    private String path;

    Parameter(Identifier id, TypeElement type) {
        this.id = id;
        this.type = type;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if ( this.path != null ) {
            throw new IllegalStateException(id());
        }
        this.path = Objects.requireNonNull(path);
    }
}

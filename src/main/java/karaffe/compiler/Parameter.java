package karaffe.compiler;

class Parameter {

    private final Identifier id;
    private final TypeElement type;

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

}

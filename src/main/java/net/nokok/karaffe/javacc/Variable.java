package net.nokok.karaffe.javacc;

public class Variable {

    private final Name name;
    private final TypeDeclaration type;

    public Variable(Name name, TypeDeclaration type) {
        this.name = name;
        this.type = type;
    }
}

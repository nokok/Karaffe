package net.nokok.karaffe.javacc;

import static java.util.Objects.requireNonNull;

public class Type {

    private final Name name;

    private final boolean isTypeParameter;
    private final boolean isInterface;

    public Type(Name name) {
        requireNonNull(name);
        String typeName = name.toString();
        char head = typeName.charAt(0);
        boolean isLowerCaseHead = Character.isLowerCase(head);
        int nameLength = typeName.length();
        if ( isLowerCaseHead && nameLength == 1 ) {
            isTypeParameter = true;
        } else if ( isLowerCaseHead && nameLength > 1 ) {
            throw new RuntimeException(CompileErrorMessage.TYPENAME_MUST_BE_START_WITH_UPPER_CASE.toString());
        } else {
            isTypeParameter = false;
        }
        this.name = name;
        this.isInterface = name.startsWith("I");
    }

    public Type(String name) {
        this(new Name(name));
    }

    public boolean isTypeParameter() {
        return isTypeParameter;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public Name getTypeName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}

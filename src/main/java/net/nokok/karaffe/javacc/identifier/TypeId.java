package net.nokok.karaffe.javacc.identifier;

import static java.util.Objects.requireNonNull;
import net.nokok.karaffe.javacc.CompileErrorMessage;
import net.nokok.karaffe.javacc.Name;
import net.nokok.karaffe.javacc.Token;

public class TypeId extends Identifier {

    public static final TypeId Any = new TypeId("Any");

    private final boolean isTypeParameter;
    private final boolean isInterface;
    private final boolean isAny;

    public TypeId(Name name) {
        super(name);
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
        this.isInterface = name.startsWith("I");
        this.isAny = name.toString().equals("Any");
    }

    public TypeId(String name) {
        this(new Name(name));
    }

    public TypeId(Token token) {
        this(new Name(token.image));
    }

    public boolean isTypeParameter() {
        return isTypeParameter;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isAny() {
        return isAny;
    }

    public Name getTypeName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}

package karaffe.compiler;

import java.util.Collections;
import java.util.List;

class TypeElement {

    static TypeElement none() {
        return new TypeElement(Identifier.none(), Collections.emptyList());
    }

    private final Identifier id;
    private final List<Identifier> targ;

    TypeElement(Identifier id, List<Identifier> targ) {
        this.id = id;
        this.targ = targ;
    }

    public String id() {
        return id.id();
    }

    public List<Identifier> args() {
        return Collections.unmodifiableList(targ);
    }

    @Override
    public String toString() {
        return "(tpe:" + String.join(" ", id.toString(), targ.toString()) + ")";
    }

}

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

    public void doResolve() {
        if ( !targ.isEmpty() ) {
            return;
        }
        this.targ.addAll(Context.INSTANCE.resolveByTypeElementId(this));
    }

    @Override
    public String toString() {
        return "(tpe:" + String.join(" ", "Id: " + id.toString(), "Type: " + targ.toString()) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == null ) {
            return false;
        }
        if ( obj instanceof TypeElement ) {
            TypeElement that = (TypeElement) obj;
            if ( this.id.equals(that.id) ) {
                if ( this.targ.size() == that.targ.size() ) {
                    for ( int i = 0; i < this.targ.size(); i++ ) {
                        if ( !this.targ.get(i).softEquals(that.targ.get(i)) ) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}

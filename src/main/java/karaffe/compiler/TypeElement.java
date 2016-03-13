package karaffe.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TypeElement {

    static TypeElement none() {
        return new TypeElement(Identifier.none(), Collections.emptyList());
    }

    private final Identifier id;
    private final List<Identifier> targ;
    private final List<Identifier> resolvedType;

    TypeElement(Identifier id, List<Identifier> targ) {
        this.id = id;
        this.targ = targ;
        this.resolvedType = new ArrayList<>();
    }

    public String id() {
        return id.id();
    }

    public List<Identifier> args() {
        return Collections.unmodifiableList(targ);
    }

    public List<Identifier> resolvedType() {
        return resolvedType;
    }

    public boolean isResolved() {
        return !resolvedType.isEmpty();
    }

    public void doResolve() {
        if ( isResolved() ) {
            return;
        }
        this.resolvedType.addAll(Context.INSTANCE.resolveByTypeElementId(this));
    }

    @Override
    public String toString() {
        return "(tpe:" + String.join(" ", "Id: " + id.toString(), "Type: " + targ.toString()) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == null ) {
            System.out.println("null");
            return false;
        }
        if ( obj instanceof TypeElement ) {
            TypeElement that = (TypeElement) obj;

            if ( !this.id.softEquals(that.id) ) {
                System.out.println("1");
                return false;
            }
            if ( this.resolvedType.size() != that.resolvedType.size() ) {
                System.out.println("2");
                return false;
            }
            for ( int i = 0; i < this.resolvedType.size(); i++ ) {
                if ( !this.resolvedType.get(i).softEquals(that.resolvedType.get(i)) ) {
                    System.out.println("3");
                    return false;
                }
            }

            if ( this.targ.size() != that.targ.size() ) {
                System.out.println("4");
                return false;
            }

            for ( int i = 0; i < this.targ.size(); i++ ) {
                if ( !this.targ.get(i).softEquals(that.targ.get(i)) ) {
                    System.out.println("5");
                    return false;
                }
            }
            return true;

        } else {
            System.out.println("not TypeElement");
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, targ, resolvedType);
    }

    void setType(List<Identifier> idList) {
        if ( !resolvedType.isEmpty() ) {
            resolvedType.clear();
        }
        this.resolvedType.addAll(idList);
    }

}

package karaffe.compiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UnionTypeElement extends TypeElement {

    private final Set<TypeElement> union;

    public UnionTypeElement(TypeElement t1, TypeElement t2) {
        super(new Identifier("__UnionType__"), new ArrayList<>(0));
        this.union = new HashSet<>();
        if ( t1 instanceof UnionTypeElement ) {
            UnionTypeElement u1 = (UnionTypeElement) t1;
            this.union.addAll(u1.getUnionSet());
        }
        if ( t2 instanceof UnionTypeElement ) {
            UnionTypeElement u2 = (UnionTypeElement) t2;
            this.union.addAll(u2.getUnionSet());
        }
        this.union.add(t1);
        this.union.add(t2);

    }

    public boolean hasType(TypeElement typeElement) {
        return this.union.contains(typeElement);
    }

    public Set<TypeElement> getUnionSet() {
        return union;
    }

    @Override
    public String toString() {
        return "(UnionType:" + union + ")";
    }

}

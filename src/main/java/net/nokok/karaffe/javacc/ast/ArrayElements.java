package net.nokok.karaffe.javacc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayElements extends Literal<List<ArrayElement>> implements Iterable<ArrayElement> {

    public static final ArrayElements EMPTY = new ArrayElements();

    public ArrayElements() {
        super(new ArrayList<>());
    }

    public ArrayElements(List<ArrayElement> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onArrayElements(this);
    }

    public void addElement(ArrayElement element) {
        value.add(element);
    }

    public Class<?> getHeadElementType() {
        if (value.isEmpty()) {
            return UndefinedValue.class;
        } else {
            return value.get(0).getClass();
        }
    }

    public String getHeadElementTypeHash() {
        return HashGenerator.javaIdentifier(this.getHeadElementType());
    }

    public int getArrayLength() {
        return value.size();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public Iterator<ArrayElement> iterator() {
        return value.iterator();
    }

    @Override
    public String nodeIdentifier() {
        return "ArrayElements";
    }
}

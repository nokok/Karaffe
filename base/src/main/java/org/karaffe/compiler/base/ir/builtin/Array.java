package org.karaffe.compiler.base.ir.builtin;

public class Array<T extends KaraffeIRType> implements KaraffeIRType {
    private Object[] values;
    public int length;

    public Array(int size) {
        this.values = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) this.values[index];
    }

    public void set(int index, T value) {
        this.values[index] = value;
        this.length = this.values.length;
    }

    public int length() {
        return this.values.length;
    }

}

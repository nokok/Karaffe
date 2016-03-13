package karaffe.core;

import java.util.Objects;

public class Int implements
    JavaPrimitiveType,
    ComparableOp,
    Equals {

    private final int value;

    public Int(String i) {
        this(Integer.valueOf(i));
    }

    public Int(int i) {
        this.value = i;
    }

    @Override
    public boolean equals(JavaPrimitiveType that) {
        Objects.requireNonNull(that);
        return this.value == that.toInt();
    }

    @Override
    public boolean notEquals(JavaPrimitiveType that) {
        Objects.requireNonNull(that);
        return this.value != that.toInt();
    }

    public Int plus(Int that) {
        Objects.requireNonNull(that);
        return new Int(this.value + that.value);
    }

    public Int minus(Int that) {
        Objects.requireNonNull(that);
        return new Int(this.value - that.value);
    }

    public Int mul(Int that) {
        Objects.requireNonNull(that);
        return new Int(this.value * that.value);
    }

    public Double div(Int that) {
        Objects.requireNonNull(that);
        return new Double(this.value / that.value);
    }

    @Override
    public int toInt() {
        return value;
    }

    @Override
    public long toLong() {
        return value;
    }

    @Override
    public float toFloat() {
        return value;
    }

    @Override
    public double toDouble() {
        return value;
    }

    @Override
    public boolean lessThan(Int that) {
        Objects.requireNonNull(that);
        return this.value < that.value;
    }

    @Override
    public boolean greaterThan(Int that) {
        return this.value > that.value;
    }

    @Override
    public boolean lessThanEqualTo(Int that) {
        return this.value <= that.value;
    }

    @Override
    public boolean greaterThanEqualTo(Int that) {
        return this.value >= that.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}

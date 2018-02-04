package karaffe.core;

import java.util.Objects;

public class Int extends Any {

    private final int value;

    public Int(int value) {
        this.value = value;
    }

    public Int(Integer value) {
        this.value = Objects.requireNonNull(value.intValue());
    }

    public Int plus(Int other) {
        return new Int(this.value + other.value);
    }

    public Int minus(Int other) {
        return new Int(this.value - other.value);
    }

    public Int mul(Int other) {
        return new Int(this.value * other.value);
    }

    public Double div(Int other) {
        return new Double(this.value / other.value);
    }

}

package karaffe.core;

import java.util.Objects;

public class Double extends Any {
    private final double value;

    public Double(double value) {
        this.value = value;
    }

    public Double(java.lang.Double value) {
        this.value = Objects.requireNonNull(value.doubleValue());
    }
}

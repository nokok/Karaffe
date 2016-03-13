package karaffe.core;

public class Double {

    private final double value;

    public Double(double d) {
        this.value = d;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}

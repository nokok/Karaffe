package karaffe.core;

public class Int {
    private int value;

    public Int(int value) {
        this.value = value;
    }

    public Int plus(Int other) {
        return new Int(this.value + other.value);
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(this.value);
    }
}

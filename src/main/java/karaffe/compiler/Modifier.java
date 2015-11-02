package karaffe.compiler;

class Modifier {

    private final int flag;

    Modifier(int flag) {
        this.flag = flag;
    }

    public int flag() {
        return flag;
    }

    @Override
    public String toString() {
        return "(modifier " + flag + ")";
    }

}

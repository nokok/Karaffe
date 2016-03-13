package karaffe.compiler;

class Modifier {

    public static final String SPECIAL_CONST;

    static {
        SPECIAL_CONST = "CONST";
    }

    private final String specialName;
    private final int flag;

    Modifier(int flag) {
        this.specialName = "";
        this.flag = flag;
    }

    Modifier(String specialName, int flag) {
        this.specialName = specialName;
        this.flag = flag;
    }

    public int flag() {
        return flag;
    }

    public String getSpecialName() {
        return this.specialName;
    }

    public boolean isSpecialModifier() {
        return !this.specialName.isEmpty();
    }

    @Override
    public String toString() {
        return "(modifier " + "SpecialName:" + specialName + ", FLAG:" + flag + ")";
    }

}

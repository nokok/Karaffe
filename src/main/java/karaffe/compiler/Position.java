package karaffe.compiler;

class Position {

    private final int column;
    private final int line;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        if ( line == -1 || column == -1 ) {
            return "(auto-generated-pos)";
        }
        return "(pos " + line + "," + column + ")";
    }

}

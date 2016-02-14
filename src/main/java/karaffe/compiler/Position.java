package karaffe.compiler;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }

}

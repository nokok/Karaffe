package karaffe.compiler;

import java.util.List;
import java.util.Objects;

public class ImportDef implements Statement {

    private final List<Identifier> fqcn;
    private final Position pos;
    private String path;

    ImportDef(List<Identifier> il, int illeft, int ilright) {
        this.fqcn = il;
        this.pos = new Position(illeft, ilright);
        il.forEach(i -> i.setParent(this));
        Context.INSTANCE.add(this);
    }

    public void setPath(String path) {
        if ( this.path != null ) {
            throw new IllegalStateException();
        }
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Class<?> inferredType() {
        return Void.class;
    }

    public String simpleIdent() {
        return fqcn.get(fqcn.size() - 1).id();
    }

    @Override
    public String toString() {
        return "(import-def " + fqcn + pos + ")";
    }

}

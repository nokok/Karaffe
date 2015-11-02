package karaffe.compiler;

import java.util.List;

public class ImportDef implements Statement {

    private final List<Identifier> fqcn;
    private final Position pos;

    ImportDef(List<Identifier> il, int illeft, int ilright) {
        this.fqcn = il;
        this.pos = new Position(illeft, ilright);
        Context.INSTANCE.add(this);
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

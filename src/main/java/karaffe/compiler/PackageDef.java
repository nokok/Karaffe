package karaffe.compiler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static java.util.stream.Collectors.toList;

class PackageDef implements Statement {

    private final List<Identifier> packageName;
    private final Position pos;
    private String path;

    public static PackageDef none() {
        return new PackageDef(Collections.emptyList(), -1, -1);
    }

    PackageDef(List<Identifier> l, int lleft, int lright) {
        this.packageName = l;
        this.pos = new Position(lleft, lright);
        Context.INSTANCE.setPackageDef(this);
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

    public String toPath(String delimiter) {
        if ( packageName.isEmpty() ) {
            return "";
        }
        return String.join(delimiter, packageName.stream().map(i -> i.id()).collect(toList()));
    }

    @Override
    public String toString() {
        return "(package-def " + packageName + pos + ")";
    }

}

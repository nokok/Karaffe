/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.name;

import java.util.Objects;
import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.visitor.Visitor;

public class AmbiguousName extends AbstractNode {

    private final Optional<AST> name;
    private final Identifier id;

    public AmbiguousName(AmbiguousName name, Identifier id) {
        this.name = Optional.ofNullable(name);
        this.id = id;
        addChildren(name);
        addChildren(id);
    }

    public AmbiguousName(Object name, Object id) {
        this.name = Optional.ofNullable((AST) name);
        this.id = Objects.requireNonNull((Identifier) id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.ambiguousName(this);
    }

    public Optional<AST> name() {
        return name;
    }

    public Optional<Identifier> id() {
        return Optional.ofNullable(id);
    }

    public boolean hasName() {
        return name != null;
    }

    public boolean hasId() {
        return id != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(AmbiguousName:");
        if (id != null) {
            builder.append(id.toString());
        }
        if (name != null) {
            builder.append(",");
            builder.append(name.toString());
        }
        return builder.append(")").toString();
    }

    /**
     * @return hoge.fuga.piyo
     */
    public String toPath() {
        return toPath(".");
    }

    public String toPath(String delimiter) {
        if (name.isPresent() == false) {
            return id.get();
        }
        return AmbiguousName.class.cast(name.get()).toPath(delimiter) + delimiter + id.get();
    }

}

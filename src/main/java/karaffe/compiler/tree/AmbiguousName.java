/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.visitor.Visitor;

public class AmbiguousName extends AbstractNode {

    private final AmbiguousName name;
    private final Identifier id;

    public AmbiguousName(AmbiguousName name, Identifier id) {
        this.name = name;
        this.id = id;
    }

    public AmbiguousName(Identifier id) {
        this(null, id);
    }

    @Override
    public void accept(Visitor visitor) {
    }

}

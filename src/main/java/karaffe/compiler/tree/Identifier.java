/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.visitor.Visitor;

public class Identifier extends AbstractNode {

    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public Identifier(Object id) {
        this((String) id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.identifier(this);
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return "(Identifier:" + id + ")";
    }

    public String name() {
        return id;
    }
}

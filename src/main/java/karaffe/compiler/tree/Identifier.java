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

    @Override
    public void accept(Visitor visitor) {
    }

    public String id() {
        return id;
    }
}

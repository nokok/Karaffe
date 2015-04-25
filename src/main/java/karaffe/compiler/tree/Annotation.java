/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import karaffe.compiler.visitor.Visitor;

public class Annotation extends AbstractNode {

    private final AST name;

    public Annotation(Object n) {
        this.name = (AST) n;
        addChildren(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.annotation(this);
    }

}

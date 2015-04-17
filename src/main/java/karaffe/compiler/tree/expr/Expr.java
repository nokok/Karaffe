/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.expr;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class Expr extends AbstractNode {

    private final AST expr;

    public Expr(Object e) {
        this.expr = (AST) e;
        addChildren(expr);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.expr(this);
    }

}

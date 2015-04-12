/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class AutoDeclBlock extends AbstractNode {

    private final AST autoDeclList;

    public AutoDeclBlock(Object l) {
        this.autoDeclList = (AST) l;
        children.add(autoDeclList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.autoDeclBlock(this);
    }

    @Override
    public String toString() {
        return "(AutoDeclBlock:" + autoDeclList.toString() + ")";
    }

}

/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.name;

import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class PackageOrTypeName extends AbstractNode {

    public PackageOrTypeName(Object name) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.packageOrTypeName(this);
    }

}

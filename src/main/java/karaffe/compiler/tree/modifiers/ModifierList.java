/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.modifiers;

import java.util.Optional;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class ModifierList extends AbstractNode {

    private final AST modifier;
    private final Optional<AST> modifierList;

    public ModifierList(Object m, Object l) {
        this.modifier = (AST) m;
        this.modifierList = Optional.ofNullable((AST) l);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.modifierList(this);
    }

}

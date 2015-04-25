/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.modifiers;

import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;

public class PrivateModifier extends AbstractNode implements ASMConvertible<Integer> {

    @Override
    public void accept(Visitor visitor) {
        visitor.privateModifier(this);
    }

    @Override
    public Integer toNode() {
        return Opcodes.ACC_PRIVATE;
    }

}

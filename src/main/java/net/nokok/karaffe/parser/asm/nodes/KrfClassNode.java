/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.ASTFieldDecl;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.AvailableTypes;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.tree.ClassNode;

public class KrfClassNode {

    private final AvailableTypes availableTypes = null;

    private final ClassNode classNode = new ClassNode();

    private final ParserVisitor visitor = new ParserDefaultVisitor() {
        @Override
        public Object visit(ASTFieldDecl node, Object data) throws ParserException {
            KrfFieldNode fieldNode = new KrfFieldNode(classNode.name, node);
            return null;
        }
    };

    public byte[] getByteCode() {
        return null;
    }
}

/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.SimpleNode;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;

public class NodeUtil {

    private final SimpleNode node;

    public NodeUtil(SimpleNode node) {
        this.node = node;
    }

    public <T extends Node> Optional<T> findFirstNode(Class<T> nodeClazz) {
        return findFirstNode(node, nodeClazz);
    }

    public <T extends Node> Optional<T> findFirstNode(SimpleNode node, Class<T> nodeClazz) {
        String nodeClassName = nodeClazz.getName();
        if (node.getClass().getName().equals(nodeClassName)) {
            return Optional.of(nodeClazz.cast(node));
        } else {
            for (int i = 0; i < node.jjtGetNumChildren(); i++) {
                SimpleNode child = (SimpleNode) node.jjtGetChild(i);
                Optional<T> result = findFirstNode(child, nodeClazz);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    public <T extends Node> T forceGetFindFirstNode(Class<T> nodeClazz) {
        return findFirstNode(nodeClazz).get();
    }

    public <T extends Node> List<T> collectNodes(Class<T> clazz) {
        return collectNode(node, clazz);
    }
    
    public <T extends Node> List<T> collectNode(Node node,Class<T> clazz){
        List<T> nodes = new ArrayList<>();
        for(int i = 0;i<node.jjtGetNumChildren();i++) {
            Node child = node.jjtGetChild(i);
            if(child.getClass().getName().equals(clazz.getName())) {
                nodes.add(clazz.cast(child));
            }
            if(child.jjtGetNumChildren() > 0) {
                nodes.addAll(collectNode(child, clazz));
            }
        }
        return nodes;
    }
    
    public Optional<List<ClassNode>> getClassNode() {
        throw new UnsupportedOperationException();
    }

    public InsnList getInsnList() {
        InsnVisitor insnVisitor = new InsnVisitor(node);
        return insnVisitor.getInsnList();
    }
}

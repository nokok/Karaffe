/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.util.Optional;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.SimpleNode;

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
}

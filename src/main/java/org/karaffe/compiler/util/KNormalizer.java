package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.base.Node;

public class KNormalizer {
    public Node normalize(Node node) {
        if (node.isKNormalizable()) {
            return Node.KNormalize(node);
        }
        List<Node> normalized = new ArrayList<>();
        for (Node child : node.getChildren()) {
            normalized.add(normalize(child));
        }
        node.replaceChildren(normalized);
        return node;
    }

}
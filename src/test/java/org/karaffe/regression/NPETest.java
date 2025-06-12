package org.karaffe.regression;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NPETest {

    @Test
    void testTreeParentChildRelationship() {
        Tree child = TreeFactory.newTree(NodeType.Identifier);
        Tree parent = TreeFactory.newTree(NodeType.Body, child);

        assertNotNull(child.getParent());
        assertEquals(parent, child.getParent());
    }
}

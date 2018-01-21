package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.NodeList;

public class NodeListTest {
    private final Apply child1 = new Apply(new Empty());
    private final Apply child2 = new Apply(new Empty());
    private final Apply child3 = new Apply(new Empty());
    private final Apply child4 = new Apply(new Empty());
    private final Apply child5 = new Apply(new Empty());
    private final Apply child6 = new Apply(new Empty());

    @Test
    public void testFlatten() {
        // [1,2,3]
        NodeList nodelist = new NodeList(child1, child2, child3);
        NodeList newNodeList = new NodeList(nodelist.flatten());
        assertEquals(newNodeList.toString(), nodelist.toString());
    }

    @Test
    public void testFlatten2() {
        // [[1,2,3], [4,5]]
        NodeList newNodeList = new NodeList(new NodeList(new NodeList(child1, child2, child3), new NodeList(child4, child5)).flatten());
        assertEquals(new NodeList(child1, child2, child3, child4, child5).toString(), newNodeList.toString());
    }

    @Test
    public void testFlatten3() {
        // [[1,2,3], [4,[5,6]]]
        NodeList nodelist = new NodeList(new NodeList(new NodeList(child1, child2, child3), new NodeList(child4, new NodeList(child5, child6))).flatten());
        assertEquals(new NodeList(child1, child2, child3, child4, child5, child6).toString(), nodelist.toString());
    }
}

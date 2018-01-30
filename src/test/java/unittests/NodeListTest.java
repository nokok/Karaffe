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
        final NodeList nodelist = new NodeList(this.child1, this.child2, this.child3);
        final NodeList newNodeList = new NodeList(nodelist.flatten());
        assertEquals(newNodeList.toString(), nodelist.toString());
    }

    @Test
    public void testFlatten2() {
        // [[1,2,3], [4,5]]
        final NodeList newNodeList = new NodeList(new NodeList(new NodeList(this.child1, this.child2, this.child3), new NodeList(this.child4, this.child5)).flatten());
        assertEquals(new NodeList(this.child1, this.child2, this.child3, this.child4, this.child5).toString(), newNodeList.toString());
    }

    @Test
    public void testFlatten3() {
        // [[1,2,3], [4,[5,6]]]
        final NodeList nodelist = new NodeList(new NodeList(new NodeList(this.child1, this.child2, this.child3), new NodeList(this.child4, new NodeList(this.child5, this.child6))).flatten());
        assertEquals(new NodeList(this.child1, this.child2, this.child3, this.child4, this.child5, this.child6).toString(), nodelist.toString());
    }
}

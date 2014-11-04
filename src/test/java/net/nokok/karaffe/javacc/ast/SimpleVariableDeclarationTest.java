package net.nokok.karaffe.javacc.ast;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class SimpleVariableDeclarationTest {

    private final Program program;
    private final List<ASTNode> nodes;

    public SimpleVariableDeclarationTest() throws ParseException {
        program = new KaraffeParser("x = 0").parse();
        nodes = program.getNodes();
    }

    @Test
    public void testNodeSize() {
        assertThat(nodes.size(), is(2));
    }

    @Test
    public void testTreeClass() {
        assertThat(nodes.get(0).getClass(), is(VariableDeclaration.class.getClass()));
        assertThat(nodes.get(1).getClass(), is(EndOfFileStatement.class.getClass()));
    }

    @Test
    public void testVariableType() {
        VariableDeclaration node = (VariableDeclaration) nodes.get(0);
        assertThat(node.getTypeString(), is(TypeId.UNKNOWN_TYPE.getName()));
    }

    @Test
    public void testVariableName() {
        VariableDeclaration node = (VariableDeclaration) nodes.get(0);
        assertThat(node.getNameString(), is("x"));
    }

    @Test
    public void testVariableNode() {
        VariableDeclaration node = (VariableDeclaration) nodes.get(0);
        assertThat(node.getNode().getClass(), is(IntLiteral.class.getClass()));
    }

    @Test
    public void testLongVariableName() throws Exception {
        Program p = new KaraffeParser("hogehogehogehogehogehogehogehogehogehogehogehoge = 0").parse();
        List<ASTNode> nodes = p.getNodes();
        assertThat(nodes.size(), is(2));
        assertThat(nodes.get(0).getClass(), is(VariableDeclaration.class.getClass()));
        assertThat(nodes.get(1).getClass(), is(EndOfFileStatement.class.getClass()));
    }

}

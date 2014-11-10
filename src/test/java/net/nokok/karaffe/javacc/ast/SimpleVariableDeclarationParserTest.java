package net.nokok.karaffe.javacc.ast;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SimpleVariableDeclarationParserTest {

    private final Program program;
    private final List<ASTNode> nodes;

    public SimpleVariableDeclarationParserTest() throws ParseException {
        program = new KaraffeParser("x = 0\n").parse();
        nodes = program.getNodes();
    }

    @Test
    public void testNodeSize() {
        assertThat(nodes.size(), is(2));
    }

    @Test
    public void testTreeClass() {
        assertTrue(nodes.get(0) instanceof VariableDeclaration);
        assertTrue(nodes.get(1) instanceof EndOfFileStatement);
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
        assertTrue(node.getNode() instanceof IntLiteral);
    }

    @Test
    public void testLongVariableName() throws Exception {
        Program p = new KaraffeParser("hogehogehogehogehogehogehogehogehogehogehogehoge = 0\n").parse();
        List<ASTNode> nodes = p.getNodes();
        assertThat(nodes.size(), is(2));
        assertTrue(nodes.get(0) instanceof VariableDeclaration);
        VariableDeclaration d = (VariableDeclaration) nodes.get(0);
        assertThat(d.getNameString(), is("hogehogehogehogehogehogehogehogehogehogehogehoge"));
        assertTrue(nodes.get(1) instanceof EndOfFileStatement);
    }

}

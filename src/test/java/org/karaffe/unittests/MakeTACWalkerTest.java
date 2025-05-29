package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.walker.MakeTACWalker;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakeTACWalkerTest {

    @Test
    void testPrintlnEmpty() { // Renamed from "println()"
        // setup:
        final int[] i = {0}; // Using an array to be modifiable in inner class

        MakeTACWalker walker = new MakeTACWalker() {
            @Override
            public void onApply(Tree tree) { // public visibility is correct
                super.onApply(tree);
                i[0]++;
            }
        };

        Tree tree = NodeType.Block.create();
        Tree applyNode = NodeType.Apply.create();
        Tree emptyNode = NodeType.Empty.create();
        Tree varNameNode = NodeType.VarName.create("println");
        Tree argumentsNode = NodeType.Arguments.create();
        
        // Correct Tree Construction: parentNode.in(child1, child2, ...)
        applyNode.in(emptyNode, varNameNode, argumentsNode);
        tree.in(applyNode);


        String before = tree.toString();
        walker.walk(tree);
        String after = tree.toString();

        // expect:
        assertEquals(before, after);
        assertEquals(1, i[0]);
    }

    @Test
    void testPrintlnWithArgument() { // Renamed from "println(1)"
        // setup:
        MakeTACWalker walker = new MakeTACWalker();
        Tree tree = NodeType.Body.create();
        Tree applyNode = NodeType.Apply.create();
        Tree emptyNode = NodeType.Empty.create();
        Tree varNameNode = NodeType.VarName.create("println");
        Tree argumentsNode = NodeType.Arguments.create();
        Tree argumentNode = NodeType.Argument.create();
        Tree intLiteralNode = NodeType.IntLiteral.create("1");

        // Correct Tree Construction:
        argumentNode.in(intLiteralNode);
        argumentsNode.in(argumentNode);
        applyNode.in(emptyNode, varNameNode, argumentsNode);
        tree.in(applyNode);

        String before = tree.toString();
        walker.walk(tree);
        String after = tree.toString();

        // expect:
        assertEquals(before, after);
    }

    @Test
    void testAddition() { // Renamed from "1 + 2"
        // setup:
        MakeTACWalker walker = new MakeTACWalker();
        Tree tree = NodeType.Body.create();
        Tree applyNode = NodeType.Apply.create();
        Tree intLiteral1 = NodeType.IntLiteral.create("1");
        Tree binOpPlus = NodeType.BinOp.create("+");
        Tree argumentsNode = NodeType.Arguments.create();
        Tree argumentNode = NodeType.Argument.create();
        Tree intLiteral2 = NodeType.IntLiteral.create("2");

        // Correct Tree Construction:
        argumentNode.in(intLiteral2);
        argumentsNode.in(argumentNode);
        applyNode.in(intLiteral1, binOpPlus, argumentsNode);
        tree.in(applyNode);

        // Expected tree structure after TAC transformation
        Tree expectedTacTree = NodeType.Body.create();
        Tree defVar0 = NodeType.DefVar.create();
        defVar0.in(NodeType.Identifier.create("$0"), NodeType.TypeName.create("__ANY__"), NodeType.IntLiteral.create("1"));
        
        Tree defVar1 = NodeType.DefVar.create();
        defVar1.in(NodeType.Identifier.create("$1"), NodeType.TypeName.create("__ANY__"), NodeType.IntLiteral.create("2"));

        Tree applyTac = NodeType.Apply.create();
        Tree varName0 = NodeType.VarName.create("$0");
        Tree binOpPlusTac = NodeType.BinOp.create("+");
        Tree argumentsTac = NodeType.Arguments.create();
        Tree argumentTac = NodeType.Argument.create();
        Tree varName1 = NodeType.VarName.create("$1");
        
        argumentTac.in(varName1);
        argumentsTac.in(argumentTac);
        applyTac.in(varName0, binOpPlusTac, argumentsTac);
        
        expectedTacTree.in(defVar0, defVar1, applyTac);
        String actualExpectedTreeString = expectedTacTree.toString(); // String from constructed TAC tree
        
        // This is the literal string expected by the Spock test for the final TAC form.
        // Corrected the missing quote in TypeName("__ANY__", []) -> TypeName("__ANY__", [])
        String spockExpectedTacString = "Body (\"\", [DefVar (\"\", [Identifier (\"$0\", []), TypeName (\"__ANY__\", []), IntLiteral (\"1\", [])]), DefVar (\"\", [Identifier (\"$1\", []), TypeName (\"__ANY__\", []), IntLiteral (\"2\", [])]), Apply (\"\", [VarName (\"$0\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [VarName (\"$1\", [])])])])])";
        
        // expect:
        // Initial state of the tree before walk
        String initialTreeString = "Body (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])])])";
        assertEquals(initialTreeString, tree.toString());
        
        walker.walk(tree); // Transformation happens here
        
        // Pre-check: Ensure the manually constructed expectedTacTree.toString() matches the Spock literal.
        assertEquals(spockExpectedTacString, actualExpectedTreeString, "Pre-check of expected string representation from constructed TAC tree");
        
        // Final check: Ensure the walked tree matches the Spock literal.
        assertEquals(spockExpectedTacString, tree.toString(), "Tree after walk should match expected TAC form (Spock literal)");
    }
}

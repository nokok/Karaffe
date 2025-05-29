package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatApplyWalkerTest {

    @Test
    void testSimpleAdditionChain() { // Renamed from "1 + 2 + 3 + 4 + 5"
        // setup:
        Tree tree = NodeType.FlatApply.create();
        tree.in(
            NodeType.IntLiteral.create("1"),
            NodeType.BinOp.create("+"),
            NodeType.IntLiteral.create("2"),
            NodeType.BinOp.create("+"),
            NodeType.IntLiteral.create("3"),
            NodeType.BinOp.create("+"),
            NodeType.IntLiteral.create("4"),
            NodeType.BinOp.create("+"),
            NodeType.IntLiteral.create("5")
        );

        FlatApplyWalker walker = new FlatApplyWalker();

        // expect:
        assertEquals("FlatApply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), IntLiteral (\"2\", []), BinOp (\"+\", []), IntLiteral (\"3\", []), BinOp (\"+\", []), IntLiteral (\"4\", []), BinOp (\"+\", []), IntLiteral (\"5\", [])])", tree.toString());
        walker.walk(tree);
        assertEquals("Apply (\"\", [Apply (\"\", [Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"4\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"5\", [])])])])", tree.toString());
    }

    @Test
    void testMixedOperations() { // Renamed from "1 + 2 * 3 - 4"
        // setup:
        Tree tree = NodeType.FlatApply.create();
        tree.in(
            NodeType.IntLiteral.create("1"),
            NodeType.BinOp.create("+"),
            NodeType.IntLiteral.create("2"),
            NodeType.BinOp.create("*"),
            NodeType.IntLiteral.create("3"),
            NodeType.BinOp.create("-"),
            NodeType.IntLiteral.create("4")
        );
        FlatApplyWalker walker = new FlatApplyWalker();

        // expect:
        assertEquals("FlatApply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), IntLiteral (\"2\", []), BinOp (\"*\", []), IntLiteral (\"3\", []), BinOp (\"-\", []), IntLiteral (\"4\", [])])", tree.toString());
        walker.walk(tree);
        assertEquals("Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), BinOp (\"*\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])])])]), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"4\", [])])])])", tree.toString());
    }

    @Test
    void testComplexExpression() { // Renamed from "a + t.foo(1, 2.toString())"
        // setup:
        Tree tree = NodeType.FlatApply.create();
        Tree applyTfoo = NodeType.Apply.create();
        Tree arguments1 = NodeType.Arguments.create();
        Tree argument1 = NodeType.Argument.create();
        argument1.in(NodeType.IntLiteral.create("1")); 
        Tree argument2 = NodeType.Argument.create();
        Tree apply2ToString = NodeType.Apply.create();
        apply2ToString.in(
            NodeType.IntLiteral.create("2"),
            NodeType.VarName.create("toString"),
            NodeType.Arguments.create() // Empty arguments
        );
        argument2.in(apply2ToString); 
        arguments1.in(argument1, argument2);
        applyTfoo.in(
            NodeType.VarName.create("t"),
            NodeType.VarName.create("foo"),
            arguments1
        );
        tree.in(
            NodeType.VarName.create("a"),
            NodeType.BinOp.create("+"),
            applyTfoo
        );
        
        String expectedTreeStr = "Apply (\"\", [VarName (\"a\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [Apply (\"\", [VarName (\"t\", []), VarName (\"foo\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])]), Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), VarName (\"toString\", []), Arguments (\"\", [])])])])])])])])";

        FlatApplyWalker walker = new FlatApplyWalker();

        // expect:
        assertEquals("FlatApply (\"\", [VarName (\"a\", []), BinOp (\"+\", []), Apply (\"\", [VarName (\"t\", []), VarName (\"foo\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])]), Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), VarName (\"toString\", []), Arguments (\"\", [])])])])])])", tree.toString());
        walker.walk(tree);
        assertEquals(expectedTreeStr, tree.toString());
    }
}

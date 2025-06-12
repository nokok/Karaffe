package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatApplyWalkerTest {

    @Test
    void testWalkSimpleAdditionChain() {
        // 1 + 2 + 3 + 4 + 5
        Tree tree = NodeType.FlatApply.create().in(
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

        assertEquals("FlatApply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), IntLiteral (\"2\", []), BinOp (\"+\", []), IntLiteral (\"3\", []), BinOp (\"+\", []), IntLiteral (\"4\", []), BinOp (\"+\", []), IntLiteral (\"5\", [])])", tree.toString());

        walker.walk(tree);

        assertEquals("Apply (\"\", [Apply (\"\", [Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"4\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"5\", [])])])])", tree.toString());
    }

    @Test
    void testWalkMixedPrecedence() {
        // 1 + 2 * 3 - 4
        Tree tree = NodeType.FlatApply.create().in(
                NodeType.IntLiteral.create("1"),
                NodeType.BinOp.create("+"),
                NodeType.IntLiteral.create("2"),
                NodeType.BinOp.create("*"),
                NodeType.IntLiteral.create("3"),
                NodeType.BinOp.create("-"),
                NodeType.IntLiteral.create("4")
        );
        FlatApplyWalker walker = new FlatApplyWalker();

        assertEquals("FlatApply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), IntLiteral (\"2\", []), BinOp (\"*\", []), IntLiteral (\"3\", []), BinOp (\"-\", []), IntLiteral (\"4\", [])])", tree.toString());

        walker.walk(tree);

        assertEquals("Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), BinOp (\"*\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])])])]), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"4\", [])])])])", tree.toString());
    }

    @Test
    void testWalkWithMethodCall() {
        Tree tree = NodeType.FlatApply.create().in(
                NodeType.VarName.create("a"),
                NodeType.BinOp.create("+"),
                NodeType.Apply.create().in(
                        NodeType.VarName.create("t"),
                        NodeType.VarName.create("foo"),
                        NodeType.Arguments.create().in(
                                NodeType.Argument.create().in(NodeType.IntLiteral.create("1")),
                                NodeType.Argument.create().in(NodeType.Apply.create().in(
                                        NodeType.IntLiteral.create("2"),
                                        NodeType.VarName.create("toString"),
                                        NodeType.Arguments.create()
                                ))
                        )
                )
        );

        String expectedInitialTreeString = "FlatApply (\"\", [VarName (\"a\", []), BinOp (\"+\", []), Apply (\"\", [VarName (\"t\", []), VarName (\"foo\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])]), Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), VarName (\"toString\", []), Arguments (\"\", [])])])])])])";

        String expectedWalkedTreeString = NodeType.Apply.create().in(
                NodeType.VarName.create("a"),
                NodeType.BinOp.create("+"),
                NodeType.Arguments.create().in(
                        NodeType.Argument.create().in(
                                NodeType.Apply.create().in(
                                        NodeType.VarName.create("t"),
                                        NodeType.VarName.create("foo"),
                                        NodeType.Arguments.create().in(
                                                NodeType.Argument.create().in(NodeType.IntLiteral.create("1")),
                                                NodeType.Argument.create().in(NodeType.Apply.create().in(
                                                        NodeType.IntLiteral.create("2"),
                                                        NodeType.VarName.create("toString"),
                                                        NodeType.Arguments.create()
                                                ))
                                        )
                                )
                        )
                )
        ).toString();

        FlatApplyWalker walker = new FlatApplyWalker();

        assertEquals(expectedInitialTreeString, tree.toString());

        walker.walk(tree);

        // Original Spock test had: expectedTree == '...' and then tree.toString() == expectedTree
        // This ensures the manually constructed expectedWalkedTreeString is what we think it is before comparing to actual walked tree.
        assertEquals("Apply (\"\", [VarName (\"a\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [Apply (\"\", [VarName (\"t\", []), VarName (\"foo\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])]), Argument (\"\", [Apply (\"\", [IntLiteral (\"2\", []), VarName (\"toString\", []), Arguments (\"\", [])])])])])])])])", expectedWalkedTreeString);
        assertEquals(expectedWalkedTreeString, tree.toString());
    }
}

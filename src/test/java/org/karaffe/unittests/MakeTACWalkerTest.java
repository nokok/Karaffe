package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.walker.MakeTACWalker;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakeTACWalkerTest {

    @Test
    void printlnTest() {
        final AtomicInteger i = new AtomicInteger(0);
        MakeTACWalker walker = new MakeTACWalker() {
            @Override
            public void onApply(Tree tree) {
                super.onApply(tree);
                i.incrementAndGet();
            }
        };
        Tree tree =
                NodeType.Block.create().in(
                        NodeType.Apply.create().in(
                                NodeType.Empty.create(),
                                NodeType.VarName.create("println"),
                                NodeType.Arguments.create()
                        )
                );
        String before = tree.toString();

        walker.walk(tree);
        String after = tree.toString();

        assertEquals(before, after);
        assertEquals(1, i.get());
    }

    @Test
    void printlnWithArgumentTest() {
        MakeTACWalker walker = new MakeTACWalker();
        Tree tree =
                NodeType.Body.create().in(
                        NodeType.Apply.create().in(
                                NodeType.Empty.create(),
                                NodeType.VarName.create("println"),
                                NodeType.Arguments.create().in(
                                        NodeType.Argument.create().in(
                                                NodeType.IntLiteral.create("1")
                                        )
                                )
                        )
                );
        String before = tree.toString();

        walker.walk(tree);
        String after = tree.toString();

        assertEquals(before, after);
    }

    @Test
    void additionTest() {
        MakeTACWalker walker = new MakeTACWalker();
        Tree tree =
                NodeType.Body.create().in(
                        NodeType.Apply.create().in(
                                NodeType.IntLiteral.create("1"),
                                NodeType.BinOp.create("+"),
                                NodeType.Arguments.create().in(
                                        NodeType.Argument.create().in(
                                                NodeType.IntLiteral.create("2")
                                        )
                                )
                        )
                );

        String expectedTreeString =
                NodeType.Body.create().in(
                        NodeType.DefVar.create().in(
                                NodeType.Identifier.create("$0"),
                                NodeType.TypeName.create("__ANY__"),
                                NodeType.IntLiteral.create("1")
                        ),
                        NodeType.DefVar.create().in(
                                NodeType.Identifier.create("$1"),
                                NodeType.TypeName.create("__ANY__"),
                                NodeType.IntLiteral.create("2")
                        ),
                        NodeType.Apply.create().in(
                                NodeType.VarName.create("$0"),
                                NodeType.BinOp.create("+"),
                                NodeType.Arguments.create().in(
                                        NodeType.Argument.create().in(
                                                NodeType.VarName.create("$1")
                                        )
                                )
                        )
                ).toString();

        assertEquals("Body (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])])])", tree.toString());

        walker.walk(tree);

        // Original Spock: expectedTree == 'string' then expectedTree == tree.toString()
        // Ensure our expectedTreeString is what we think it is first.
        assertEquals("Body (\"\", [DefVar (\"\", [Identifier (\"$0\", []), TypeName (\"__ANY__\", []), IntLiteral (\"1\", [])]), DefVar (\"\", [Identifier (\"$1\", []), TypeName (\"__ANY__\", []), IntLiteral (\"2\", [])]), Apply (\"\", [VarName (\"$0\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [VarName (\"$1\", [])])])])])", expectedTreeString);
        assertEquals(expectedTreeString, tree.toString());
    }
}

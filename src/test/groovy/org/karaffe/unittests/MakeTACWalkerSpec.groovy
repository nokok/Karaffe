package org.karaffe.unittests

import org.karaffe.compiler.frontend.karaffe.walker.MakeTACWalker
import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import spock.lang.Specification

class MakeTACWalkerSpec extends Specification {
  def "println()"() {
    setup:
    def i = 0
    def walker = new MakeTACWalker() {
      @Override
      void onApply(Tree tree) {
        super.onApply(tree)
        i++
      }
    }
    def tree =
      NodeType.Block.create().in(
        NodeType.Apply.create().in(
          NodeType.Empty.create(),
          NodeType.VarName.create("println"),
          NodeType.Arguments.create()
        )
      )
    def before = tree.toString()
    walker.walk(tree)
    def after = tree.toString()

    expect:
    after == before
    i == 1
  }

  def "println(1)"() {
    setup:
    def walker = new MakeTACWalker()
    def tree =
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
      )
    def before = tree.toString()
    walker.walk(tree)
    def after = tree.toString()

    expect:
    before == after
  }

  def "1 + 2"() {
    setup:
    def walker = new MakeTACWalker()
    def tree =
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
      )

    def expectedTree =
      NodeType.Body.create().in(
        NodeType.DefVar.create().in(
          NodeType.Identifier.create('$0'),
          NodeType.TypeName.create("__ANY__"),
          NodeType.IntLiteral.create("1")
        ),
        NodeType.DefVar.create().in(
          NodeType.Identifier.create('$1'),
          NodeType.TypeName.create("__ANY__"),
          NodeType.IntLiteral.create("2")
        ),
        NodeType.Apply.create().in(
          NodeType.VarName.create('$0'),
          NodeType.BinOp.create("+"),
          NodeType.Arguments.create().in(
            NodeType.Argument.create().in(
              NodeType.VarName.create('$1'),
            )
          )
        )
      ).toString()

    expect:
    tree.toString() == 'Body ("", [Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("2", [])])])])])'
    walker.walk(tree)
    expectedTree == 'Body ("", [DefVar ("", [Identifier ("$0", []), TypeName ("__ANY__", []), IntLiteral ("1", [])]), DefVar ("", [Identifier ("$1", []), TypeName ("__ANY__", []), IntLiteral ("2", [])]), Apply ("", [VarName ("$0", []), BinOp ("+", []), Arguments ("", [Argument ("", [VarName ("$1", [])])])])])'
    expectedTree == tree.toString()
  }
}

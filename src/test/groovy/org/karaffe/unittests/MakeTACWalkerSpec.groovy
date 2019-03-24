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
}

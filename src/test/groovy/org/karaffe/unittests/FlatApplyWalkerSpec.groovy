package org.karaffe.unittests

import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker
import org.karaffe.compiler.tree.NodeType
import spock.lang.Specification

class FlatApplyWalkerSpec extends Specification {

  def "1 + 2 + 3 + 4 + 5"() {
    setup:
    // 1 + 2 + 3 + 4 + 5
    def tree = NodeType.FlatApply.create().in(
      NodeType.IntLiteral.create("1"),
      NodeType.BinOp.create("+"),
      NodeType.IntLiteral.create("2"),
      NodeType.BinOp.create("+"),
      NodeType.IntLiteral.create("3"),
      NodeType.BinOp.create("+"),
      NodeType.IntLiteral.create("4"),
      NodeType.BinOp.create("+"),
      NodeType.IntLiteral.create("5"),
    )
    FlatApplyWalker walker = new FlatApplyWalker()

    expect:
    tree.toString() == 'FlatApply ("", [IntLiteral ("1", []), BinOp ("+", []), IntLiteral ("2", []), BinOp ("+", []), IntLiteral ("3", []), BinOp ("+", []), IntLiteral ("4", []), BinOp ("+", []), IntLiteral ("5", [])])'
    walker.walk(tree)
    tree.toString() == 'Apply ("", [Apply ("", [Apply ("", [Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("2", [])])])]), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("3", [])])])]), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("4", [])])])]), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("5", [])])])])'
  }

  def "1 + 2 * 3 - 4"() {
    setup:
    // 1 + 2 * 3 - 4
    def tree = NodeType.FlatApply.create().in(
      NodeType.IntLiteral.create("1"),
      NodeType.BinOp.create("+"),
      NodeType.IntLiteral.create("2"),
      NodeType.BinOp.create("*"),
      NodeType.IntLiteral.create("3"),
      NodeType.BinOp.create("-"),
      NodeType.IntLiteral.create("4")
    )
    FlatApplyWalker walker = new FlatApplyWalker()

    expect:
    tree.toString() == 'FlatApply ("", [IntLiteral ("1", []), BinOp ("+", []), IntLiteral ("2", []), BinOp ("*", []), IntLiteral ("3", []), BinOp ("-", []), IntLiteral ("4", [])])'
    walker.walk(tree)
    tree.toString() == 'Apply ("", [Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [Apply ("", [IntLiteral ("2", []), BinOp ("*", []), Arguments ("", [Argument ("", [IntLiteral ("3", [])])])])])])]), BinOp ("-", []), Arguments ("", [Argument ("", [IntLiteral ("4", [])])])])'
  }

  def "a + t.foo(1, 2.toString())"() {
    setup:
    def tree = NodeType.FlatApply.create().in(
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
    )

    def expectedTree = NodeType.Apply.create().in(
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
    ).toString()

    FlatApplyWalker walker = new FlatApplyWalker()

    expect:
    tree.toString() == 'FlatApply ("", [VarName ("a", []), BinOp ("+", []), Apply ("", [VarName ("t", []), VarName ("foo", []), Arguments ("", [Argument ("", [IntLiteral ("1", [])]), Argument ("", [Apply ("", [IntLiteral ("2", []), VarName ("toString", []), Arguments ("", [])])])])])])'
    walker.walk(tree)
    expectedTree == 'Apply ("", [VarName ("a", []), BinOp ("+", []), Arguments ("", [Argument ("", [Apply ("", [VarName ("t", []), VarName ("foo", []), Arguments ("", [Argument ("", [IntLiteral ("1", [])]), Argument ("", [Apply ("", [IntLiteral ("2", []), VarName ("toString", []), Arguments ("", [])])])])])])])])'
    tree.toString() == expectedTree
  }
}

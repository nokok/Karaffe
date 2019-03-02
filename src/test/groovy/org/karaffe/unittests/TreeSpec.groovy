package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.TreeFactory
import org.karaffe.compiler.util.Position
import spock.lang.Specification

class TreeSpec extends Specification {
  def "treetoString"() {
    expect:
    TreeFactory.newTree(NodeType.DefClass, "A", Position.noPos()).toString() == 'DefClass ("A", [])'
  }

  def "factory"() {
    expect:
    TreeFactory.newTree(NodeType.Identifier) != null
    TreeFactory.newTree(NodeType.Identifier, "A") != null
    TreeFactory.newTree(NodeType.Identifier, "A", Position.noPos()) != null
    TreeFactory.newTree(NodeType.Identifier, Position.noPos()) != null
  }

  def "addChild"() {
    expect:
    def tree = TreeFactory.newTree(NodeType.Identifier)
    tree.addChild(TreeFactory.newTree(NodeType.Identifier))
  }

  def "insertBefore"() {
    setup:
    def args = TreeFactory.newTree(NodeType.Arguments)
    def arg1 = TreeFactory.newTree(NodeType.Argument, "1")
    def arg2 = TreeFactory.newTree(NodeType.Argument, "2")
    args.addChild(arg2)
    arg2.insertBefore(arg1)

    expect:
    args.getChildren().toString() == '[Argument ("1", []), Argument ("2", [])]'
  }

  def "insertAfter"() {
    setup:
    def args = TreeFactory.newTree(NodeType.Arguments)
    def arg1 = TreeFactory.newTree(NodeType.Argument, "1")
    def arg2 = TreeFactory.newTree(NodeType.Argument, "2")
    def arg3 = TreeFactory.newTree(NodeType.Argument, "3")
    args.addChild(arg1)
    args.addChild(arg3)
    arg1.insertAfter(arg2)

    expect:
    args.getChildren().toString() == '[Argument ("1", []), Argument ("2", []), Argument ("3", [])]'
  }
}

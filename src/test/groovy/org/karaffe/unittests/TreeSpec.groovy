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
}

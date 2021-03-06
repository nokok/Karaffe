package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import org.karaffe.compiler.tree.TreeFactory
import org.karaffe.compiler.tree.walker.TreeWalker
import spock.lang.Specification
import spock.lang.Unroll

class TreeWalkerSpec extends Specification {

  private Tree tree

  def setup() {
    def apply = TreeFactory.newTree(NodeType.Apply)
    def a = TreeFactory.newTree(NodeType.Identifier, "A")
    def b = TreeFactory.newTree(NodeType.Identifier, "B")
    apply.addChild(a)
    apply.addChild(b)
    this.tree = apply
  }

  def "walk order"() {
    setup:
    def walker = new TreeWalker() {
      @Override
      void onApply(Tree tree) {

      }

      @Override
      void onIdentifier(Tree tree) {

      }
    }

    when:
    walker.walk(tree)

    then:
    notThrown(AssertionError)
  }

  def "bailout"() {
    setup:
    def count = 0
    def walker = new TreeWalker() {
      @Override
      void onApply(Tree tree) {
        assert false
      }

      @Override
      void onIdentifier(Tree tree) {
        count++
        bailout()
      }
    }
    def apply = TreeFactory.newTree(NodeType.Apply)
    def a = TreeFactory.newTree(NodeType.Identifier, "A")
    def b = TreeFactory.newTree(NodeType.Identifier, "B")
    apply.addChild(a)
    apply.addChild(b)

    when:
    walker.walk(apply)

    then:
    notThrown(AssertionError)

    expect:
    count == 1
  }

  @Unroll
  def "testImpl"() {
    setup:
    def walker = new TreeWalker() {}

    when:
    walker.walk(TreeFactory.newTree(nodeType))

    then:
    notThrown(IllegalStateException)

    where:
    nodeType << Arrays.asList(NodeType.values())
  }
}

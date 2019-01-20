package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.TreeFactory
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter
import org.karaffe.compiler.util.Position
import spock.lang.Specification

class TreeFormatterSpec extends Specification {
  def "singleNode"() {
    setup:
    def tree = TreeFactory.newTree(NodeType.CompilationUnit, "A", Position.noPos())
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == "CompilationUnit A"
  }

  def "singleNodeWithChlidren"() {
    setup:
    def tree = TreeFactory.newTree(NodeType.CompilationUnit, "C", Position.noPos())
    tree.addChild(TreeFactory.newTree(NodeType.Apply, "1", Position.noPos()))
    tree.addChild(TreeFactory.newTree(NodeType.Apply, "2", Position.noPos()))
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == """CompilationUnit C
                    |  Apply 1
                    |  Apply 2""".stripMargin()
  }

  def "nestedChildren"() {
    setup:
    def tree = TreeFactory.newTree(NodeType.CompilationUnit, "C", Position.noPos())
    def child1 = TreeFactory.newTree(NodeType.Apply, "1", Position.noPos())
    child1.addChild(TreeFactory.newTree(NodeType.Select, "c", Position.noPos()))
    tree.addChild(child1)
    tree.addChild(TreeFactory.newTree(NodeType.Apply, "2", Position.noPos()))
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == """CompilationUnit C
                    |  Apply 1
                    |    Select c
                    |  Apply 2""".stripMargin()
  }

}

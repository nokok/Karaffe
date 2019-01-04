package org.karaffe.unittests


import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter
import org.karaffe.compiler.util.Position
import spock.lang.Specification

class TreeFormatterSpec extends Specification {
  def "singleNode"() {
    setup:
    def tree = new Tree(NodeType.CompilationUnit, "A", Position.noPos())
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == "CompilationUnit A"
  }

  def "singleNodeWithChlidren"() {
    setup:
    def tree = new Tree(NodeType.CompilationUnit, "C", Position.noPos())
    tree.addChild(new Tree(NodeType.Apply, "1", Position.noPos()))
    tree.addChild(new Tree(NodeType.Apply, "2", Position.noPos()))
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == """CompilationUnit C
                    |  Apply 1
                    |  Apply 2""".stripMargin()
  }

  def "nestedChildren"() {
    setup:
    def tree = new Tree(NodeType.CompilationUnit, "C", Position.noPos())
    def child1 = new Tree(NodeType.Apply, "1", Position.noPos())
    child1.addChild(new Tree(NodeType.Select, "c", Position.noPos()))
    tree.addChild(child1)
    tree.addChild(new Tree(NodeType.Apply, "2", Position.noPos()))
    def formatter = new SimpleTreeFormatter()
    def result = formatter.format(tree)

    expect:
    result == """CompilationUnit C
                    |  Apply 1
                    |    Select c
                    |  Apply 2""".stripMargin()
  }

}

package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import org.karaffe.compiler.tree.TreeFormatter
import org.karaffe.compiler.tree.attr.Attribute
import org.karaffe.compiler.tree.attr.AttributeType
import spock.lang.Specification

class TreeFormatterSpec extends Specification {
    def "singleNode"() {
        setup:
        def tree = new Tree(NodeType.CompilationUnit, "A", null)
        def formatter = new TreeFormatter()
        def result = formatter.format(tree)

        expect:
        result == "CompilationUnit A"
    }

    def "singleNodeWithAttribute"() {
        setup:
        def tree = new Tree(NodeType.CompilationUnit, "B", null)
        tree.addAttribute(new Attribute(AttributeType.Modifier))
        def formatter = new TreeFormatter()
        def result = formatter.format(tree)

        expect:
        result == """CompilationUnit B [ModifierAttribute]"""
    }

    def "singleNodeWithChild"() {
        setup:
        def tree = new Tree(NodeType.CompilationUnit, "C", null)
        tree.addAttribute(new Attribute(AttributeType.Modifier))
        tree.addChild(new Tree(NodeType.Apply, "()", null))
        def formatter = new TreeFormatter()
        def result = formatter.format(tree)

        expect:
        result == """CompilationUnit C [ModifierAttribute]
                    |  Apply ()""".stripMargin()
    }

    def "singleNodeWithChlidren"() {
        setup:
        def tree = new Tree(NodeType.CompilationUnit, "C", null)
        tree.addAttribute(new Attribute(AttributeType.Modifier))
        tree.addChild(new Tree(NodeType.Apply, "1", null))
        tree.addChild(new Tree(NodeType.Apply, "2", null))
        def formatter = new TreeFormatter()
        def result = formatter.format(tree)

        expect:
        result == """CompilationUnit C [ModifierAttribute]
                    |  Apply 1
                    |  Apply 2""".stripMargin()
    }

    def "nestedChildren"() {
        setup:
        def tree = new Tree(NodeType.CompilationUnit, "C", null)
        tree.addAttribute(new Attribute(AttributeType.Modifier))
        def child1 = new Tree(NodeType.Apply, "1", null)
        child1.addChild(new Tree(NodeType.Select, "c", null))
        tree.addChild(child1)
        tree.addChild(new Tree(NodeType.Apply, "2", null))
        def formatter = new TreeFormatter()
        def result = formatter.format(tree)

        expect:
        result == """CompilationUnit C [ModifierAttribute]
                    |  Apply 1
                    |    Select c
                    |  Apply 2""".stripMargin()
    }

}

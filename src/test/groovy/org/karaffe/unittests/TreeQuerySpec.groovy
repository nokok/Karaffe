package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import spock.lang.Specification
import spock.lang.Unroll

import static org.karaffe.compiler.tree.NodeType.Body
import static org.karaffe.compiler.tree.NodeType.Identifier

class TreeQuerySpec extends Specification {
  def "dig simple"() {
    setup:
    def a1 = new Tree(Identifier, "a1")
    def tree = new Tree(Body, "body")
    tree.addChild(a1)
    def result = tree.dig(type)

    expect:
    result.isPresent()
    result.get().nodeType == type
    result.get().name == expectedName

    where:
    type       || expectedName
    Identifier || "a1"
  }

  def "dig recursive"() {
    setup:
    def i1 = new Tree(Identifier, "i1")
    def a1 = new Tree(Identifier, "a1")
    def tree = new Tree(Body, "body")
    tree.addChild(a1)
    a1.addChild(i1)
    def result = tree.dig(type as NodeType[])

    expect:
    result.isPresent()
    result.get().nodeType == expectedType
    result.get().name == expectedName

    where:
    type                     || expectedName || expectedType
    [Identifier, Identifier] || "i1"         || Identifier
  }

  @Unroll
  def "climb"() {
    setup:
    def i1 = new Tree(Identifier, "i1")
    def a1 = new Tree(Identifier, "a1")
    def tree = new Tree(Body, "body")
    tree.addChild(a1)
    a1.addChild(i1)
    def result = i1.climb(type)

    expect:
    result.isPresent()
    result.get().nodeType == type
    result.get().name == expectedName

    where:
    type       || expectedName
    Identifier || "a1"
    Body       || "body"
  }

  def "parent"() {
    setup:
    def i1 = new Tree(Identifier, "i1")
    def a1 = new Tree(Identifier, "a1")
    def tree = new Tree(Body, "body")
    tree.addChild(a1)
    a1.addChild(i1)

    expect:
    i1.getParent().getName() == "a1"
    i1.getParent().getParent().getName() == "body"
    i1.getParent().getParent().getParent() == null
  }
}

package org.karaffe.regression


import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.TreeFactory
import spock.lang.Specification

class NPE extends Specification {

  def "3"() {
    def child = TreeFactory.newTree(NodeType.Identifier)
    def parent = TreeFactory.newTree(NodeType.Body, child)

    expect:
    child.getParent() != null
    child.getParent() == (parent)
  }
}

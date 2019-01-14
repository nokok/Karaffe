package org.karaffe.unittests

import org.karaffe.compiler.frontend.karaffe.util.TypeTreeBuilder
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter
import org.karaffe.compiler.tree.formatter.TreeFormatter
import spock.lang.Specification

class TreeBuilderSpec extends Specification {
  def "Object.class"() {
    setup:
    def builder = new TypeTreeBuilder()
    def tree = builder.buildTreeFromClass(Object)
    TreeFormatter formatter = new SimpleTreeFormatter()
    def format = formatter.format(tree)
    expect:
    format == ""
  }
}

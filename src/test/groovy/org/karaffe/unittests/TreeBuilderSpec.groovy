package org.karaffe.unittests

import org.karaffe.compiler.frontend.karaffe.util.TypeTreeConverter
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter
import org.karaffe.compiler.tree.formatter.TreeFormatter
import spock.lang.Specification

class TreeBuilderSpec extends Specification {
  def "Object.class"() {
    setup:
    def builder = new TypeTreeConverter()
    def tree = builder.convert(Object)
    TreeFormatter formatter = new SimpleTreeFormatter()
    def format = formatter.format(tree)
    expect:
    format == """PackageName java.lang
                |  ClassName Object""".stripMargin()
  }
}

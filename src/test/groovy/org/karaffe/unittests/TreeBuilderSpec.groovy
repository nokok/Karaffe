package org.karaffe.unittests

import org.karaffe.compiler.frontend.karaffe.util.TypeTreeConverter
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter
import org.karaffe.compiler.tree.formatter.InternalStateFormatter
import org.karaffe.compiler.util.CompilerContext
import spock.lang.Specification

class TreeBuilderSpec extends Specification {
  def "Object.class"() {
    setup:
    def builder = new TypeTreeConverter()
    def tree = builder.convert(Object)
    InternalStateFormatter formatter = new SimpleTreeFormatter()
    def context = new CompilerContext()
    context.setUntypedTree(tree)
    def format = formatter.format(context)
    expect:
    format == """PackageName java.lang
                |  ClassName Object""".stripMargin()
  }
}

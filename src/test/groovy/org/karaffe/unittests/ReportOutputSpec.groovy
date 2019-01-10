package org.karaffe.unittests

import org.karaffe.compiler.util.report.Report
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.Position
import spock.lang.Specification

class ReportOutputSpec extends Specification {
  def "simpleReport"() {
    setup:
    def context = new CompilerContext()
    context.add(Report.newErrorReport("Title").build())

    expect:
    context.getOutputText() == "[ERROR] Title"
  }

  def "positionReport"() {
    setup:
    def context = new CompilerContext()
    context.add(Report.newErrorReport("Title").with(new Position(3, 1, "Main.krf")).build())

    expect:
    context.getOutputText() == "[ERROR] Title at 3:1:Main.krf"
  }

  def "bodyReport"() {
    def context = new CompilerContext()
    context.add(Report.newErrorReport("Title").with(new Position(3, 1, "Main.krf")).withBody("Body").build())

    expect:
    context.getOutputText() == """[ERROR] Title at 3:1:Main.krf
                                     |Body""".stripMargin()
  }

  def "reports"() {
    def context = new CompilerContext()
    context.add(Report.newErrorReport("Title1").with(new Position(3, 1, "Main.krf")).withBody("Body").build())
    context.add(Report.newErrorReport("Title2").with(new Position(3, 1, "Main.krf")).withBody("Body").build())

    expect:
    context.getOutputText() == """[ERROR] Title1 at 3:1:Main.krf
                                     |Body
                                     |[ERROR] Title2 at 3:1:Main.krf
                                     |Body""".stripMargin()
  }
}

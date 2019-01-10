package org.karaffe.unittests

import org.karaffe.compiler.util.args.Flag
import org.karaffe.compiler.util.report.Report
import org.karaffe.compiler.util.CompilerContext
import spock.lang.Specification

class CompilerContextSpec extends Specification {
  def "dry-run flag"() {
    setup:
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run"] as String[])

    expect:
    context.hasFlag(Flag.DRY_RUN)
  }

  def "duplicate flag"() {
    setup:
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run", "--dry-run"] as String[])

    expect:
    context.hasError()
  }

  def "reportText"() {
    setup:
    def ctx = new CompilerContext()
    ctx.add(Report.newInfoReport("Title").withBody("Body").build())

    expect:
    ctx.getOutputText() == """[INFO ] Title
                                 |Body""".stripMargin()
  }
}

package org.karaffe.regression

import org.karaffe.compiler.phase.util.ShowReportsPhase
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.Position
import org.karaffe.compiler.util.report.Report
import org.karaffe.compiler.util.report.ReportCode
import spock.lang.Specification

class ReportSpec extends Specification {
  def "position"() {
    def ctx = CompilerContext.createInitialContext()
    ctx.add(Report.newReport(ReportCode.ERR_FRONTEND_SYNTAX).with(new Position(1, 4, "Hoge.krf")).build())
    def p = new ShowReportsPhase()

    p.execute(ctx)

    expect:
    ctx.out.toString() == "ERROR | Syntax Error at 1:4:Hoge.krf" + System.lineSeparator()
  }
}

package org.karaffe.unittests

import org.karaffe.compiler.report.Report
import org.karaffe.compiler.report.ReportType
import org.karaffe.compiler.util.Position
import spock.lang.Specification
import spock.lang.Unroll

class ReportSpec extends Specification {

  @Unroll
  def "testException"() {
    when:
    Report.newErrorReport(title)

    then:
    thrown(ex)

    where:
    title || ex
    ""    || IllegalArgumentException
    " "   || IllegalArgumentException
    null  || NullPointerException
  }

  def "errorReport"() {
    setup:
    def report = Report.newErrorReport("Title").with(new Position(1, 10, "Main.krf")).build()

    expect:
    report.reportType == ReportType.ERROR
    report.header == "Title"
    report.position.get().line == 1
    report.position.get().column == 10
    report.position.get().sourceName == "Main.krf"
    report.toString() == "[ERROR] Title at 1:10:Main.krf"
  }

}

package org.karaffe.unittests

import org.karaffe.compiler.util.report.ReportCode
import spock.lang.Specification
import spock.lang.Unroll

class ResourceSpec extends Specification {
  @Unroll
  def "properties #code"() {
    when:
    code.toReportHeader()

    then:
    notThrown(MissingResourceException)

    where:
    code << Arrays.asList(ReportCode.values())
  }
}

package org.karaffe.unittests

import org.karaffe.compiler.util.report.ReportCode
import spock.lang.Specification
import spock.lang.Unroll

class ReportCodeSpec extends Specification {

  @Unroll
  def "testNeedVariable #source"() {
    expect:
    source.isRequireVariable() == (varCount != 0)
    source.getVarCount() == varCount
    source.isRequireBody() == requireBody
    source.isRequirePosition() == requirePosition

    where:

    source                                    || varCount || requireBody || requirePosition
    ReportCode.ERR_FRONTEND_SYNTAX            || 0        || true        || true
    ReportCode.INFO_COMPILER_INTERNAL_VERSION || 1        || false       || false
  }
}

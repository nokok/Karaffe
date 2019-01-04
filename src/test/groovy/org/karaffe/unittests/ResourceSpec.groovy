package org.karaffe.unittests

import org.karaffe.compiler.report.ReportCode
import spock.lang.Specification
import spock.lang.Unroll

class ResourceSpec extends Specification {
  @Unroll
  def "properties #code"() {
    when:
    code.toTitleName()

    then:
    notThrown(MissingResourceException)

    where:
    code << Arrays.asList(ReportCode.values())
  }

  def "resourceBundle US"() {
    def resource = ResourceBundle.getBundle("Message", Locale.US)
    expect:
    resource.getString("unknown") == "Unknown Error"
  }


  def "resourceBundle JP"() {
    def resource = ResourceBundle.getBundle("Message", Locale.JAPAN)
    expect:
    resource.getString("unknown") != "Unknown Error"
  }
}

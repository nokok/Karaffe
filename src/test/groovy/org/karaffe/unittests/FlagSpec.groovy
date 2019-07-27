package org.karaffe.unittests

import org.karaffe.compiler.util.args.Flag
import spock.lang.Specification
import spock.lang.Unroll

class FlagSpec extends Specification {

  @Unroll
  def "testResource #flag"() {
    when:
    flag.getDescription()

    then:
    notThrown(MissingResourceException)

    where:
    flag << Arrays.asList(Flag.values())
  }
}

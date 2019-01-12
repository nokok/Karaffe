package org.karaffe.unittests

import spock.lang.Specification

import javax.annotation.processing.ProcessingEnvironment

class TypeCheckerSpec extends Specification {
  def "sandbox"() {
    def types = ProcessingEnvironment.getTypeUtils()
  }
}

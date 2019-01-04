package org.karaffe.unittests

import org.karaffe.compiler.util.ClassNameValidator
import spock.lang.Specification
import spock.lang.Unroll

import static org.karaffe.compiler.util.NameValidationResult.*

class NameSpec extends Specification {

  @Unroll
  def "className #name"() {
    def validator = new ClassNameValidator()

    expect:
    validator.validate(name) == expectedResult

    where:
    name   || expectedResult
    "I"    || OK
    "Main" || OK
    "i"    || WARN_CAMEL_CASE
    null   || ERR_NULL
    ""     || ERR_EMPTY_NAME
    "👮"   || ERR_INVALID_JAVA_IDENTIFIER
    "+"    || ERR_INVALID_JAVA_IDENTIFIER
    "_"    || ERR_LAMBDA_KEYWORD
  }
}

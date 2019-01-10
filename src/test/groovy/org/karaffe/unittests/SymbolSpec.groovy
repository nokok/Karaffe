package org.karaffe.unittests

import karaffe.reflect.Symbol
import karaffe.reflect.SymbolType
import spock.lang.Specification

class SymbolSpec extends Specification {
  def "symbol"() {
    def sym = Symbol.of(SymbolType.CLASS, "name")

    expect:
    sym.toString() == "CLASS name"
  }

}

package org.karaffe.unittests

import org.karaffe.compiler.tree.attr.MethodSignature
import org.karaffe.compiler.util.MethodParameterEntry
import spock.lang.Specification

class MethodSignatureSpec extends Specification {
    def "() -> void"() {
        expect:
        new MethodSignature(void).toString() == "MethodSignature () -> void"
    }

    def "int -> void"() {
        expect:
        new MethodSignature(void, [new MethodParameterEntry("foo", int)]).toString() == "MethodSignature (foo int) -> void"
    }

    def "int, int -> void"() {
        expect:
        new MethodSignature(void, [new MethodParameterEntry("foo", int), new MethodParameterEntry("bar", int)]).toString() == "MethodSignature (foo int, bar int) -> void"
    }
}

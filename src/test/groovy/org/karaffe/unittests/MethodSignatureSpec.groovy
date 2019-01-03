package org.karaffe.unittests

import org.karaffe.compiler.tree.attr.Attribute
import org.karaffe.compiler.tree.attr.AttributeType
import org.karaffe.compiler.util.MethodParameterEntry
import spock.lang.Specification

class MethodSignatureSpec extends Specification {
    def "() -> void"() {
        setup:
        def attr = new Attribute(AttributeType.MethodSignature)
        attr.put("returnType", void)
        attr.put("parameters", [])

        expect:
        attr.toString() == "MethodSignature={returnType=void, parameters=[]}"
    }

    def "int -> void"() {
        setup:
        def attr = new Attribute(AttributeType.MethodSignature)
        attr.put("returnType", void)
        attr.put("parameters", [new MethodParameterEntry("foo", int)])

        expect:
        attr.toString() == "MethodSignature={returnType=void, parameters=[foo:int]}"
    }

    def "int, int -> void"() {
        setup:
        def attr = new Attribute(AttributeType.MethodSignature)
        attr.put("returnType", void)
        attr.put("parameters", [new MethodParameterEntry("foo", int), new MethodParameterEntry("bar", int)])
        expect:
        attr.toString() == "MethodSignature={returnType=void, parameters=[foo:int, bar:int]}"
    }
}

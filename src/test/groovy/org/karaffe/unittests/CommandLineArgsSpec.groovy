package org.karaffe.unittests

import org.karaffe.compiler.args.ArgsParser
import spock.lang.Specification

class CommandLineArgsSpec extends Specification {
    def "parse empty"() {
        setup:
        def parser = new ArgsParser()
        def optOption = parser.parse([] as String[])

        expect:
        optOption.isPresent()
        optOption.get().isEmpty()
    }

    def "illegal head"() {
        setup:
        def parser = new ArgsParser()
        def optOption = parser.parse(["-"] as String[])

        expect:
        !optOption.isPresent()
    }
}

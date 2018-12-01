package org.karaffe.integration

import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.Main
import spock.lang.Specification

class CommandSpec extends Specification {
    def "should show usage"() {
        Main.main([] as String[])
        def out = Main.getContext().getOutputText()

        expect:
        out == """Usage:
                 |  krfc <options> <sources>""".stripMargin()
    }

    def "duplicate"() {
        def context = new CompilerContext()
        context.parseRawArgs(["--dry-run", "--dry-run"] as String[])

        expect:
        !context.hasNoOutputText()
        context.getOutputText() == """Duplicated flag : --dry-run"""
    }

    def "unrecognized options"() {
        def context = new CompilerContext()
        context.parseRawArgs(["--foo"] as String[])

        expect:
        context.hasOutputText()
        context.getOutputText() == "Unrecognized option : --foo"
    }

    def "source file"() {
        def context = new CompilerContext()
        context.parseRawArgs(["src/test/resources/Main.krf"] as String[])

        expect:
        context.hasNoOutputText()
    }
}
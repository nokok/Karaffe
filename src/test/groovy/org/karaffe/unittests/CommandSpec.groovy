package org.karaffe.unittests

import org.karaffe.compiler.Main
import org.karaffe.compiler.util.CompilerContext
import spock.lang.Specification

class CommandSpec extends Specification {
    def "should show usage"() {
        Main main = new Main()
        main.run([] as String[])
        def out = main.getContext().getOutputText()

        expect:
        out == """[INFO ] Usage:
                 |krfc <options> <sources>""".stripMargin()
    }

    def "duplicate"() {
        def context = new CompilerContext()
        context.parseRawArgs(["--dry-run", "--dry-run"] as String[])

        expect:
        context.getOutputText() == """[ERROR] Duplicated flag : --dry-run"""
    }

    def "unrecognized options"() {
        def context = new CompilerContext()
        context.parseRawArgs(["--foo"] as String[])

        expect:
        context.getOutputText() == "[ERROR] Unrecognized option : --foo"
    }

    def "source file"() {
        def context = new CompilerContext()
        context.parseRawArgs(["src/test/resources/Main.krf"] as String[])

        expect:
        context.getOutputText() == ""
    }

    def "version"() {
        Main main = new Main()
        main.run(["--version"] as String[])
        def out = main.getContext().getOutputText()

        expect:
        out == "[INFO ] Karaffe compiler version: 0.1.0"
    }

}
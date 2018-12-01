package org.karaffe.integration

import org.karaffe.compiler.CompilerContext
import org.karaffe.compiler.KaraffeCompiler
import spock.lang.Specification

class CommandSpec extends Specification {
    def "should show usage"() {
        def context = new CompilerContext()
        context.rawArgs = []
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def out = compiler.out()

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
    }
}
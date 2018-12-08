package org.karaffe.unittests

import org.karaffe.compiler.util.CompilerContext
import spock.lang.Specification

class CompilerContextSpec extends Specification {
    def "dry-run flag"() {
        setup:
        def context = new CompilerContext()
        context.parseRawArgs(["--dry-run"] as String[])

        expect:
        context.hasFlag("dry-run")
    }
}

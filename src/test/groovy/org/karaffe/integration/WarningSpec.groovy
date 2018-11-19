package org.karaffe.integration

import org.karaffe.compiler.CompilerContext
import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class WarningSpec extends Specification {
    def "naming"() {
        setup:
        def context = new CompilerContext()
        context.parseRawArgs(["--dry-run"] as String[])
        context.addSource(KaraffeSource.fromString(source))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == size
        context.outputText == outputText

        where:
        source              || size || outputText
        "class simpleClass" || 1    || "[warning] class name must be PascalCase : simpleClass"
    }
}

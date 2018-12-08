package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths

class FileCreationSpec extends Specification {

    def "Valid SimpleClass.class"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString("class SimpleClass"))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 1
        context.outputFiles.get(Paths.get("SimpleClass.class")) != null
        context.hasNoOutputText()
        Files.exists(Paths.get("SimpleClass.class"))

        cleanup:
        try {
            Files.delete(Paths.get("SimpleClass.class"))
        } catch (NoSuchFileException e) {
            // ignore
        }
    }

    def "Invalid Class 1A"() {
        setup:
        def context = new CompilerContext()
        context.parseRawArgs(["--dry-run"] as String[])
        context.addSource(KaraffeSource.fromString("class 1A"))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 0
        context.outputFiles.get(Paths.get("A.class")) == null
        !context.hasNoOutputText()
        !Files.exists(Paths.get("A.class"))
        !Files.exists(Paths.get("1A.class"))

        cleanup:
        try {
            Files.delete(Paths.get("A.class"))
            Files.delete(Paths.get("1A.class"))
        } catch (NoSuchFileException e) {
            // ignore
        }
    }

    def "parse error"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString("""class Hoge { entrypoint { print("Hello" + 1) }}"""))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 0
        context.outputFiles.get(Paths.get("A.class")) == null
        context.getOutputText() == "[ERROR]'karaffe.core.String'+'karaffe.core.Int' is not applicable at 1:40 in <unknown>"
        !Files.exists(Paths.get("Hoge.class"))

        cleanup:
        try {
            Files.delete(Paths.get("Hoge.class"))
        } catch (NoSuchFileException e) {
            // ignore
        }
    }
}

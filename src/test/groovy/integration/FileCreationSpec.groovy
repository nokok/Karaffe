package integration

import org.karaffe.compiler.CompilerContext
import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class FileCreationSpec extends Specification {

    def "Valid SimpleClass.class"() {
        setup:
        def context = new CompilerContext()
        context.setRawArgs(["--dry-run"] as String[])
        context.addSource(KaraffeSource.fromString("class SimpleClass"))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 1
        context.outputFiles.get(Paths.get("SimpleClass.class")) != null
        context.hasNoOutputText()
        !Files.exists(Paths.get("SimpleClass.class"))
    }

    def "Invalid Class 1A"() {
        setup:
        def context = new CompilerContext()
        context.setRawArgs(["--dry-run"] as String[])
        context.addSource(KaraffeSource.fromString("class 1A"))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 0
        context.outputFiles.get(Paths.get("A.class")) == null
        !context.hasNoOutputText()
        !Files.exists(Paths.get("A.class"))
        !Files.exists(Paths.get("1A.class"))
    }
}

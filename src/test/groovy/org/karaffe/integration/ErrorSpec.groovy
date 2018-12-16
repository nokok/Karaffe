package org.karaffe.integration

import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.KaraffeCompiler
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths

class ErrorSpec extends Specification {
    def "String+int"() {
        setup:
        try {
            Files.delete(Paths.get("Main.krf"))
        } catch (NoSuchFileException e) {
            //ignored
        }
        def path = Files.write(Paths.get("Main.krf"), """class Hoge { entrypoint {print("Hello World" + 1)}}""".getBytes())
        def context = new CompilerContext()
        context.parseRawArgs([path.toString()] as String[])
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        Files.delete(Paths.get("Main.krf"))

        expect:
        context.outputFiles.size() == 0
        context.outputText == """[ERROR] 'karaffe.core.String'+'karaffe.core.Int' is not applicable at 1:45:Main.krf"""
    }
}

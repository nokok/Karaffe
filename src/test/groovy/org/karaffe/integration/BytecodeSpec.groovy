package org.karaffe.integration

import net.nokok.azm.ClassReader
import net.nokok.azm.Opcodes
import net.nokok.azm.tree.ClassNode
import net.nokok.azm.tree.analysis.Analyzer
import net.nokok.azm.tree.analysis.SimpleVerifier
import org.karaffe.compiler.CompilerContext
import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.file.Paths

class BytecodeSpec extends Specification {

    def "entrypoint"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString(
                """class Main {
                  |  entrypoint {
                  |  }
                  |}""".stripMargin()
        ))
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def byteCode = context.outputFiles.get(Paths.get("Main.class"))
        ClassReader reader = new ClassReader(byteCode)
        ClassNode classNode = new ClassNode(Opcodes.ASM6)
        reader.accept(classNode, ClassReader.SKIP_DEBUG)
        def mainMethod = classNode.methods.get(0)
        def analyzer = new Analyzer<>(new SimpleVerifier())
        analyzer.analyze(classNode.name, mainMethod)


        expect:
        classNode.access == Opcodes.ACC_PUBLIC
        classNode.methods.size() == 1
        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
    }

    def "helloWorld"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString(
                """class Main {
                  |  entrypoint {
                  |    print("Hello World!")
                  |  }
                  |}""".stripMargin()
        ))
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def byteCode = context.outputFiles.get(Paths.get("Main.class"))
        ClassReader reader = new ClassReader(byteCode)
        ClassNode classNode = new ClassNode(Opcodes.ASM6)
        reader.accept(classNode, ClassReader.SKIP_DEBUG)
        def mainMethod = classNode.methods.get(0)
        def analyzer = new Analyzer<>(new SimpleVerifier())
        analyzer.analyze(classNode.name, mainMethod)


        expect:
        classNode.access == Opcodes.ACC_PUBLIC
        classNode.methods.size() == 1
        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
        mainMethod.maxStack == 1
        mainMethod.maxLocals == 1
        mainMethod.instructions.get(0).opcode == Opcodes.LDC
        mainMethod.instructions.get(1).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(2).opcode == Opcodes.RETURN

    }

    def "1+2"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString(
                """class Main {
                  |  entrypoint {
                  |    print(1 + 2)
                  |  }
                  |}""".stripMargin()
        ))
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def byteCode = context.outputFiles.get(Paths.get("Main.class"))
        ClassReader reader = new ClassReader(byteCode)
        ClassNode classNode = new ClassNode(Opcodes.ASM6)
        reader.accept(classNode, ClassReader.SKIP_DEBUG)
        def mainMethod = classNode.methods.get(0)
        def analyzer = new Analyzer<>(new SimpleVerifier())
        analyzer.analyze(classNode.name, mainMethod)


        expect:
        classNode.access == Opcodes.ACC_PUBLIC
        classNode.methods.size() == 1

        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
        mainMethod.instructions.size() == 5
        mainMethod.instructions.get(0).opcode == Opcodes.ICONST_1
        mainMethod.instructions.get(1).opcode == Opcodes.ICONST_2
        mainMethod.instructions.get(2).opcode == Opcodes.IADD
        mainMethod.instructions.get(3).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(4).opcode == Opcodes.RETURN
    }

    def "intLiteral"() {
        setup:
        def context = new CompilerContext()
        context.addSource(KaraffeSource.fromString(
                """class Main {
                  |  entrypoint {
                  |    print(1)
                  |  }
                  |}""".stripMargin()
        ))
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def byteCode = context.outputFiles.get(Paths.get("Main.class"))
        ClassReader reader = new ClassReader(byteCode)
        ClassNode classNode = new ClassNode(Opcodes.ASM6)
        reader.accept(classNode, ClassReader.SKIP_DEBUG)
        def mainMethod = classNode.methods.get(0)
        def analyzer = new Analyzer<>(new SimpleVerifier())
        analyzer.analyze(classNode.name, mainMethod)

        expect:
        mainMethod.instructions.size() == 3
        mainMethod.instructions.get(0).opcode == Opcodes.ICONST_1
        mainMethod.instructions.get(1).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(2).opcode == Opcodes.RETURN
    }
}

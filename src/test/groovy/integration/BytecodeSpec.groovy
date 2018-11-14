package integration

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

//        public static void main(java.lang.String[]);
//        descriptor: ([Ljava/lang/String;)V
//                      flags: (0x0009) ACC_PUBLIC, ACC_STATIC
//                      Code:
//                              stack=2, locals=1, args_size=1
//                      0: getstatic     #12                 // Field java/lang/System.out:Ljava/io/PrintStream;
//                      3: ldc           #14                 // String Hello World!
//                      5: invokevirtual #20                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
//                      8: return

        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
        mainMethod.maxStack == 2
        mainMethod.maxLocals == 1
        mainMethod.instructions.get(0).opcode == Opcodes.GETSTATIC
        mainMethod.instructions.get(1).opcode == Opcodes.LDC
        mainMethod.instructions.get(2).opcode == Opcodes.INVOKEVIRTUAL
        mainMethod.instructions.get(3).opcode == Opcodes.RETURN

    }
}

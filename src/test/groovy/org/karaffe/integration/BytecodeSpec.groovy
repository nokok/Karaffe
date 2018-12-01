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
        //   public static void main(java.lang.String[]);
        //    descriptor: ([Ljava/lang/String;)V
        //    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        //    Code:
        //      stack=3, locals=1, args_size=1
        //         0: new           #8                  // class karaffe/core/String
        //         3: dup
        //         4: ldc           #10                 // String Hello World!
        //         6: invokespecial #14                 // Method karaffe/core/String."<init>":(Ljava/lang/String;)V
        //         9: invokestatic  #20                 // Method karaffe/core/Console.println:(Ljava/lang/Object;)V
        //        12: return
        classNode.access == Opcodes.ACC_PUBLIC
        classNode.methods.size() == 1
        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
        mainMethod.maxStack == 3
        mainMethod.maxLocals == 1
        mainMethod.instructions.get(0).opcode == Opcodes.NEW
        mainMethod.instructions.get(1).opcode == Opcodes.DUP
        mainMethod.instructions.get(2).opcode == Opcodes.LDC
        mainMethod.instructions.get(3).opcode == Opcodes.INVOKESPECIAL
        mainMethod.instructions.get(4).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(5).opcode == Opcodes.RETURN

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
        //  public static void main(java.lang.String[]);
        //    descriptor: ([Ljava/lang/String;)V
        //    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        //    Code:
        //      stack=4, locals=1, args_size=1
        //         0: new           #8                  // class karaffe/core/Int
        //         3: dup
        //         4: iconst_1
        //         5: invokespecial #12                 // Method karaffe/core/Int."<init>":(I)V
        //         8: new           #8                  // class karaffe/core/Int
        //        11: dup
        //        12: iconst_2
        //        13: invokespecial #12                 // Method karaffe/core/Int."<init>":(I)V
        //        16: invokevirtual #16                 // Method karaffe/core/Int.plus:(Lkaraffe/core/Int;)Lkaraffe/core/Int;
        //        19: invokestatic  #22                 // Method karaffe/core/Console.println:(Ljava/lang/Object;)V
        //        22: return

        classNode.access == Opcodes.ACC_PUBLIC
        classNode.methods.size() == 1
        mainMethod.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
        mainMethod.name == "main"
        mainMethod.desc == "([Ljava/lang/String;)V"
        mainMethod.instructions.size() == 11
        mainMethod.maxStack == 4
        mainMethod.maxLocals == 1
        mainMethod.instructions.get(0).opcode == Opcodes.NEW
        mainMethod.instructions.get(1).opcode == Opcodes.DUP
        mainMethod.instructions.get(2).opcode == Opcodes.ICONST_1
        mainMethod.instructions.get(3).opcode == Opcodes.INVOKESPECIAL
        mainMethod.instructions.get(4).opcode == Opcodes.NEW
        mainMethod.instructions.get(5).opcode == Opcodes.DUP
        mainMethod.instructions.get(6).opcode == Opcodes.ICONST_2
        mainMethod.instructions.get(7).opcode == Opcodes.INVOKESPECIAL
        mainMethod.instructions.get(8).opcode == Opcodes.INVOKEVIRTUAL
        mainMethod.instructions.get(9).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(10).opcode == Opcodes.RETURN
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
        mainMethod.instructions.size() == 6
        mainMethod.maxStack == 3
        mainMethod.maxLocals == 1
        mainMethod.instructions.get(0).opcode == Opcodes.NEW
        mainMethod.instructions.get(1).opcode == Opcodes.DUP
        mainMethod.instructions.get(2).opcode == Opcodes.ICONST_1
        mainMethod.instructions.get(3).opcode == Opcodes.INVOKESPECIAL
        mainMethod.instructions.get(4).opcode == Opcodes.INVOKESTATIC
        mainMethod.instructions.get(5).opcode == Opcodes.RETURN
    }
}

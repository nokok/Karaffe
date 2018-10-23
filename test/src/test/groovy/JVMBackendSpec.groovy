import net.nokok.azm.ClassReader
import net.nokok.azm.Opcodes
import net.nokok.azm.tree.ClassNode
import org.karaffe.compiler.backend.jvm.KaraffeCompilerBackend
import org.karaffe.compiler.backend.jvm.util.IllegalNameException
import org.karaffe.compiler.backend.jvm.util.JavaIdentifier
import org.karaffe.compiler.base.BackendType
import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.ir.IR
import org.karaffe.compiler.base.ir.Module
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Paths

class JVMBackendSpec extends Specification {
    def "simple class"() {
        IR ir = IR.newIR()
        ir.add(new Module())
        CompilerContext context = new CompilerContextImpl()
        context.setTargetBackendType(BackendType.JVM)
        context.setIR(ir)
        def backend = KaraffeCompilerBackend.getBackend(context)
        backend.run(context)
        def bytecodes = context.getBytecodes()
        byte[] byteCode = bytecodes.get(Paths.get("A.class"))
        ClassReader classReader = new ClassReader(byteCode)
        ClassNode classNode = new ClassNode(Opcodes.ASM6)
        classReader.accept(classNode, ClassReader.EXPAND_FRAMES)

        // source
        """
        class A {}
        """

        // IR
        """
        module A {}
        """

        expect:
        classNode.access == Opcodes.ACC_PUBLIC
        classNode.version == Opcodes.V1_8
        classNode.name == "A"
        classNode.interfaces == []
        classNode.superName == "java/lang/Object"
        classNode.methods == []
        classNode.fields == []
        classNode.module == null
    }
//
//    def "field"() {
//        IR ir = IR.newIR()
//        def module = new Module()
//        ir.add(module)
//        def field = new FieldDecl("f", new Int16())
//        module.add(field)
//        CompilerContext context = new CompilerContextImpl()
//        context.setTargetBackendType(BackendType.JVM)
//        context.setIR(ir)
//        def backend = KaraffeCompilerBackend.getBackend(context)
//        backend.run(context)
//        def bytecodes = context.getBytecodes()
//        byte[] byteCode = bytecodes.get(Paths.get("A.class"))
//        ClassReader classReader = new ClassReader(byteCode)
//        ClassNode classNode = new ClassNode(Opcodes.ASM6)
//        classReader.accept(classNode, ClassReader.EXPAND_FRAMES)
//
//        expect:
//        classNode.access == Opcodes.ACC_PUBLIC
//        classNode.version == Opcodes.V1_8
//        classNode.name == "A"
//        classNode.interfaces == []
//        classNode.superName == "java/lang/Object"
//        classNode.methods == []
//        classNode.module == null
//        classNode.fields.size() == 1
//        classNode.fields.get(0).name == "f"
//    }

    @Unroll
    def "illegal java identifier #id"() {
        when:
        new JavaIdentifier(id)

        then:
        thrown(IllegalNameException)

        where:
        id  || _
        "1" || _
        "!" || _
        " " || _
    }

    @Unroll
    def "legal java identifier #id"() {
        when:
        new JavaIdentifier(id)

        then:
        notThrown(IllegalNameException)

        where:
        id     || _
        "i"    || _
        "AB"   || _
        "_ABC" || _
    }

}

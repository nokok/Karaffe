import org.karaffe.compiler.backend.jvm.KaraffeCompilerBackend
import org.karaffe.compiler.base.BackendType
import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.FrontendType
import org.karaffe.compiler.base.task.Task
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import spock.lang.Specification

class MIRBackendSpec extends Specification {
    private CompilerContext runBackend(String source) {
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        context.setFrontendType(FrontendType.KARAFFE)
        context.setTargetBackendType(BackendType.JVM)
        Task frontend = KaraffeCompilerFrontend.getFrontend(context)
        def frontendTaskResult = frontend.run(context)
        if (frontendTaskResult != TaskResult.SUCCESSFUL) {
            throw new RuntimeException("Frontend Task failed")
        }
        def backend = KaraffeCompilerBackend.getBackend(context)
        def run = backend.run(context)
        if (run != TaskResult.SUCCESSFUL) {
            throw new RuntimeException("Backend Task failed")
        }
        return context
    }

    private boolean assertBytecodeFileCount(CompilerContext context, int count) {
        return context.getBytecodes().size() == count
    }

    def "simpleClass1"() {
        setup:
        def context = runBackend("""class A
""")

        expect:
        !context.hasErrorReport()
        !context.hasAnyReport()
        assertBytecodeFileCount(context, 1)
    }

    def "mainMethod"() {
        setup:
        def context = runBackend("""class A {
  main {
  }
}""")

        expect:
        context.getIR().toString() == """"""
    }
}

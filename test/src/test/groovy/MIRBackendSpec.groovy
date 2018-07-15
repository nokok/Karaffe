import org.karaffe.compiler.backend.jvm.BackendType
import org.karaffe.compiler.backend.jvm.KaraffeCompilerBackend
import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.mir.Instructions
import org.karaffe.compiler.base.task.Task
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import spock.lang.Specification

class MIRBackendSpec extends Specification {
    private Instructions parse(String source) {
        Task frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        def result = frontend.run(context)
        if (result != TaskResult.SUCCESS) {
            throw new RuntimeException()
        }
        return context.getInstructions()
    }

    private CompilerContext runBackend(String source) {
        def instructions = parse(source)
        Task backend = KaraffeCompilerBackend.getBackend(BackendType.JVM)
        CompilerContext context = new CompilerContextImpl()
        context.setInstructions(instructions)
        def run = backend.run(context)
        if (run != TaskResult.SUCCESS) {
            throw new RuntimeException()
        }
        return context
    }

    private boolean assertBytecodeFileCount(CompilerContext context, int count) {
        return context.getBytecodes().size() == count
    }

    def "simpleClass1"() {
        setup:
        def context = runBackend("""class A""")

        expect:
        context.getInstructions().toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginConstructor #A#<init>():void
[       <no-pos>] EndConstructor #A#<init>():void
[       <no-pos>] BeginClass #A
[       <no-pos>] EndClass #A
[       <no-pos>] EndBlock #"""
        !context.hasErrorReport()
        !context.hasAnyReport()
        assertBytecodeFileCount(context, 1)
    }
}

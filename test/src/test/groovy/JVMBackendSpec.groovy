import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.launcher.KaraffeCompiler
import spock.lang.Specification

class JVMBackendSpec extends Specification {

    def "should be generated SimpleClass.class"() {
        setup:
        def file = new File("SimpleClass.class")
        file.delete()
        def compiler = KaraffeCompiler.newCompilerWithDebug("class SimpleClass {}")
        def result = compiler.run()

        expect:
        result == TaskResult.SUCCESSFUL
        file.exists()
    }
}

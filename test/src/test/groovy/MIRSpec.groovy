import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import spock.lang.Specification

class MIRSpec extends Specification {
    def "mir"() {
        setup:
        def context = new CompilerContextImpl()
        def frontend = KaraffeCompilerFrontend.getFrontend(context)
        context.addSourceFile(SourceFile.fromLiteral("class SimpleClass {}"))
        def run = frontend.run(context)
        def ir = context.IR

        expect:
        run.successful
        ir != null
        ir.toString() ==
                """class SimpleClass"""
    }
}

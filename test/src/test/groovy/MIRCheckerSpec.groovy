import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.FrontendType
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import spock.lang.Specification

class MIRCheckerSpec extends Specification {

    private CompilerContext parseAndGenerateInstructions(String source) {
        KaraffeCompilerFrontend frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        def mayBeInstructions = frontend.exec(context)
        return mayBeInstructions.get()
    }

    def "unknown load"() {

    }
}

import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import org.karaffe.compiler.mir.InstructionType
import org.karaffe.compiler.mir.block.Begin
import org.karaffe.compiler.mir.block.End
import org.karaffe.compiler.mir.util.InstructionList
import org.karaffe.compiler.mir.util.Label
import spock.lang.Specification

class MIRSpec extends Specification {

    def "Minimum Program"() {
        setup:
        KaraffeCompilerFrontend frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(""))
        def mayBeInstructions = frontend.exec(context)

        def expected = new InstructionList()
        def rootLabel = Label.createRootLabel()
        expected.add(new Begin(InstructionType.PROGRAM, rootLabel))
        expected.add(new End(rootLabel))

        expect:
        mayBeInstructions.isPresent()
        def instructions = mayBeInstructions.get()
        instructions == expected
    }
}

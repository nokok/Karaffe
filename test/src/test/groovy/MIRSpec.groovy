import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import org.karaffe.compiler.mir.InstructionType
import org.karaffe.compiler.mir.Instructions
import org.karaffe.compiler.mir.block.Begin
import org.karaffe.compiler.mir.block.End
import org.karaffe.compiler.mir.constant.Const
import org.karaffe.compiler.mir.io.Store
import org.karaffe.compiler.mir.util.InstructionList
import org.karaffe.compiler.mir.util.Label
import org.karaffe.compiler.mir.variable.ValDef
import spock.lang.Specification

class MIRSpec extends Specification {

    private Instructions parseAndGenerateInstructions(String source) {
        KaraffeCompilerFrontend frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        def mayBeInstructions = frontend.exec(context)
        return mayBeInstructions.get()
    }

    def "Minimum Program"() {
        setup:
        def expected = new InstructionList()
        def rootLabel = Label.createRootLabel()
        expected.add(new Begin(InstructionType.PROGRAM, rootLabel))
        expected.add(new End(rootLabel))

        expect:
        parseAndGenerateInstructions("") == expected
    }

    def "simpleLet1"() {
        setup:
        def expected = new InstructionList()
        def rootLabel = Label.createRootLabel()
        def beginProgram = new Begin(InstructionType.PROGRAM, rootLabel)
        def valName = new Label(rootLabel, "a")
        def valDef = new ValDef(valName, "Int")
        def constant0 = new Const("0")
        def store0 = new Store(valName)
        def endProgram = new End(rootLabel)
        expected.add(beginProgram)
        expected.add(valDef)
        expected.add(constant0)
        expected.add(store0)
        expected.add(endProgram)
        def actual = parseAndGenerateInstructions("let a Int = 0")

        expect:
        actual == expected
        ((Begin) actual.get(0)).getLabel() == new Label("#")
        ValDef actualValDef = actual.get(1)
        actualValDef.getPosition() == Position.ofRange(Position.of("<unknown>", 1, 0), Position.of("<unknown>", 1, 12))
        actualValDef.getValName() == new Label("#a")
        actualValDef.getTypeName() == "Int"
        Const actualConst0 = actual.get(2)
        actualConst0.getPosition() == Position.of("<unknown>", 1, 12)
    }
}

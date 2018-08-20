import org.karaffe.compiler.base.mir.instructions.InstructionType
import org.karaffe.compiler.base.mir.instructions.block.BeginBlock
import org.karaffe.compiler.base.mir.instructions.block.EndBlock
import org.karaffe.compiler.base.mir.instructions.transformer.InstructionTransformer
import org.karaffe.compiler.base.mir.instructions.util.InstructionList
import org.karaffe.compiler.base.mir.instructions.util.Label
import spock.lang.Specification
import util.FrontendUtil

class InstructionTransformerSpec extends Specification {
    def "Empty transformer does nothing"() {
        setup:
        def d = InstructionTransformer.create().build()
        def instruction = FrontendUtil.parseAndGenerateInstructions(source)

        expect:
        d.transform(instruction).toString() == instruction.toString()

        where:
        _ || source
        _ || """class A"""
    }

    def "Remove Rule"() {
        setup:
        def transformer = InstructionTransformer.create().onRemove(InstructionType.BEGINBLOCK).build()
        def instruction = new InstructionList()
        instruction.add(new BeginBlock(new Label("A")))
        instruction.add(new BeginBlock(new Label("B")))
        instruction.add(new BeginBlock(new Label("C")))
        instruction.add(new EndBlock(new Label("C")))
        instruction.add(new EndBlock(new Label("B")))
        instruction.add(new EndBlock(new Label("A")))

        expect:
        def transformed = transformer.transform(instruction)
        transformed.size() == 3
        transformed.stream().allMatch({ te -> te.getInstType() == InstructionType.ENDBLOCK })

    }
}


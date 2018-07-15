import org.karaffe.compiler.base.mir.block.BeginClass
import org.karaffe.compiler.base.mir.block.BeginMethod
import org.karaffe.compiler.base.mir.util.Label
import spock.lang.Specification
import spock.lang.Unroll

class MIRInstructionSpec extends Specification {

    @Unroll
    def "BeginClassSpec #label"() {
        setup:
        def beginClass = new BeginClass(new Label(label))

        expect:
        beginClass.getLabel().toString() == label
        beginClass.getClassName() == className

        where:
        label || className
        "#A"  || "A"
    }

    @Unroll
    def "BeginMethodSpec #label"() {
        setup:
        def beginMethod = new BeginMethod(new Label(label))

        expect:
        beginMethod.getLabel().toString() == label
        beginMethod.getMethodName() == methodName
        beginMethod.getReturnTypeName() == returnType
        beginMethod.getParameters() == parameters

        where:
        label                                  || methodName   | parameters   | returnType
        "#A#methodName(parameters):ReturnType" || "methodName" | "parameters" | "ReturnType"
        "#A#a():V"                             || "a"          | ""           | "V"
    }
}

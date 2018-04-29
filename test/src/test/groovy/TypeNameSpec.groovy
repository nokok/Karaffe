import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName
import spock.lang.Specification

class TypeNameSpec extends Specification {

    def "SimpleNameは単純名の型名のみを扱う"() {
        setup:
        def name = new TypeName("SimpleName")

        expect:
        !name.isNormalizable()
    }
}
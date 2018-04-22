import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName
import spock.lang.Specification

class TypeNameSpec extends Specification {

    def "SimpleNameは単純名の型名のみを扱う"() {
        setup:
        def name = new SimpleName("SimpleName")

        expect:
        !name.isNormalizable()
    }
}
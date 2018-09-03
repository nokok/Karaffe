import org.karaffe.compiler.base.attr.NormalizedTree
import spock.lang.Specification

class AttributeSpec extends Specification {
    def "FlagAttribute toString"() {
        def flagAttribute = new NormalizedTree()

        expect:
        flagAttribute.toString() == "NormalizedTree"
    }
}

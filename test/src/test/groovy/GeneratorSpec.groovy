import org.karaffe.compiler.base.generator.Generator
import spock.lang.Specification

class GeneratorSpec extends Specification {
    def "Generator"() {
        setup:
        def generator = Generator.defaultElementIdGenerator(true)
        def generator2 = Generator.defaultElementIdGenerator(true)

        expect:
        generator.generate() == "_0"
        generator.generate() == "_1"
        generator.generate() == "_2"
        generator2.generate() == "_0"
        generator2.generate() == "_1"
        generator2.generate() == "_2"
        generator.generate() == "_3"
    }
}

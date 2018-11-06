import org.karaffe.compiler.launcher.KaraffeCompiler
import spock.lang.Specification

class End2EndSpec extends Specification {
    def "show usage"() {
        setup:
        def compiler = new KaraffeCompiler(new String[0])
        def result = compiler.run()

        expect:
        result.failed
    }
}

import org.karaffe.compiler.base.util.SourceFile
import spock.lang.Specification

import java.nio.file.Paths

class SourceFileSpec extends Specification {

    def "SourceFileは存在するファイルのみ扱う"() {
        when:
        new SourceFile(new File("foo.krf"))

        then:
        thrown(UncheckedIOException)
    }

    def "SourceFileにディレクトリを示すファイルを渡すと例外をスローする"() {
        setup:
        def f = new File(".")

        expect:
        f.isDirectory()

        when:
        new SourceFile(f)

        then:
        thrown(IllegalArgumentException)
    }

    def "SourceFile#toPathは絶対パスのPathを返す"() {
        setup:
        def f = Paths.get("tests", "test_resources", "pos", "Empty.krf")
        def s = new SourceFile(f.toFile())

        expect:
        s.toPath() == f.toAbsolutePath()
    }
}
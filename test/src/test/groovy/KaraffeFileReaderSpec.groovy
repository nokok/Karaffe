import org.karaffe.compiler.frontend.karaffe.phase.file.KaraffeFileReader
import org.karaffe.compiler.frontend.karaffe.transformer.PhaseException
import spock.lang.Specification

class KaraffeFileReaderSpec extends Specification {

    def "ファイルが読み込める場合は正常終了してCommonTokenStreamを返す"() {
        setup:
        File krfFile = File.createTempFile("readerspec", ".krf")
        KaraffeFileReader reader = new KaraffeFileReader()

        def result = reader.run(krfFile)
        def stream = result.getOrThrow()
        expect:
        stream.sourceName == krfFile.getAbsolutePath()
        !reader.changed()
        stream.size() == 0

        cleanup:
        krfFile.delete()
    }

    def "ファイルが読み込めない場合は例外をthrowする"() {
        setup:
        KaraffeFileReader reader = new KaraffeFileReader()

        when:
        reader.run(new File("foobarbaz"))

        then:
        def e = thrown(PhaseException)
        e.cause.class == FileNotFoundException.class
    }
}


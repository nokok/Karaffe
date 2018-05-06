import org.karaffe.compiler.frontend.karaffe.transformer.KaraffeFileReader
import org.karaffe.compiler.frontend.karaffe.transformer.PassAbortedException
import spock.lang.Specification

class KaraffeFileReaderSpec extends Specification {

    def "ファイルが読み込める場合は正常終了してCommonTokenStreamを返す"() {
        setup:
        File krfFile = File.createTempFile("readerspec", ".krf")
        KaraffeFileReader reader = new KaraffeFileReader()

        def stream = reader.run(krfFile)
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
        def e = thrown(PassAbortedException)
        e.cause.class == FileNotFoundException.class
    }
}


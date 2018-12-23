package org.karaffe.unittests

import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.CharBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class KaraffeSourceSpec extends Specification {

    def "toStringMethod"() {
        expect:
        KaraffeSource.fromString("Source").toString() == "Source"
    }

    def "testEquals"() {
        expect:
        CharBuffer.wrap(KaraffeSource.fromString("A")) == CharBuffer.wrap(KaraffeSource.fromString("A"))
    }

    def "fromPath"() {
        setup:
        def path = Paths.get("Source.krf")
        Files.write(path, "class Hoge {}".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)
        def p = KaraffeSource.fromPath(path)
        Files.delete(path)

        expect:
        p.sourceName == "Source.krf"
    }

    def "fromRelativePath"() {
        setup:
        def p = KaraffeSource.fromPath(Paths.get("src/test/resources/Main.krf"))

        expect:
        p.sourceName == "src/test/resources/Main.krf"
    }
}

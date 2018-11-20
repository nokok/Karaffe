package org.karaffe.unittests

import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.CharBuffer

class KaraffeSourceSpec extends Specification {
    def "toStringMethod"() {
        expect:
        KaraffeSource.fromString("Source").toString() == "Source"
    }

    def "testEquals"() {
        CharBuffer.wrap(KaraffeSource.fromString("A")) == CharBuffer.wrap(KaraffeSource.fromString("A"))
    }
}

package org.karaffe.unittests

import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class KaraffeSourceSpec extends Specification {
    def "toStringMethod"() {
        expect:
        KaraffeSource.fromString("Source").toString() == "Source"
    }
}

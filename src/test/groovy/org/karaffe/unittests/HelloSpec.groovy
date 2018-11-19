package org.karaffe.unittests

import spock.lang.Specification

class HelloSpec extends Specification {
    def "Hello spec"() {
        expect:
        1 == 1
    }
}

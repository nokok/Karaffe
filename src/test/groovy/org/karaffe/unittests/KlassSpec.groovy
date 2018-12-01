package org.karaffe.unittests

import org.karaffe.compiler.Klass
import spock.lang.Specification
import spock.lang.Unroll

class KlassSpec extends Specification {
    @Unroll
    def "isOperatorApplicable == true #source"() {
        setup:
        def klass = new Klass(source)

        expect:
        klass.arithmeticOperatorApplicable

        where:
        source << [
                int,
                float,
                long,
                double,
                karaffe.core.Int,
                karaffe.core.String,
        ]
    }

    @Unroll
    def "isOperatorApplicable == false #source"() {
        setup:
        def klass = new Klass(source)

        expect:
        !klass.arithmeticOperatorApplicable

        where:
        source << [
                boolean,
                Object
        ]
    }
}

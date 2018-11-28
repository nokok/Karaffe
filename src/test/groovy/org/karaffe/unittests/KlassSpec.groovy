package org.karaffe.unittests

import org.karaffe.compiler.Klass
import spock.lang.Specification
import spock.lang.Unroll

class KlassSpec extends Specification {
    @Unroll
    def "isOperatorApplicable #source"() {
        setup:
        def klass = new Klass(source)

        expect:
        klass.arithmeticOperatorApplicable == arithmeticOperatorApplicable

        where:
        source              || arithmeticOperatorApplicable
        int                 || (true)
        float               || (true)
        boolean             || (false)
        karaffe.core.Int    || (true)
        karaffe.core.String || (true)
        Object              || (false)
    }
}

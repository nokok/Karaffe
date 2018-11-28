package org.karaffe.unittests

import org.karaffe.compiler.OperatorResolver
import spock.lang.Specification
import spock.lang.Unroll

class OperatorResolverSpec extends Specification {
    @Unroll
    def "valid plusOperator #source"() {
        when:
        new OperatorResolver(source)

        then:
        noExceptionThrown()

        where:
        source || _
        int    || _
        long   || _
        float  || _
        double || _
    }

    @Unroll
    def "invalid plusOperator #source to #type"() {
        when:
        new OperatorResolver(source)

        then:
        thrown(type)

        where:
        source  || type
        boolean || IllegalArgumentException
        char    || IllegalArgumentException
        null    || NullPointerException
    }
}

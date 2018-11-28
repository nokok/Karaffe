package org.karaffe.unittests

import org.karaffe.compiler.MethodResolver
import spock.lang.Specification
import spock.lang.Unroll

class MethodResolverSpec extends Specification {
    static class Base {
        public void doSomething() {

        }
    }

    static class Derived extends Base {
        public void doSomething2() {

        }

        public void number(Number i) {

        }
    }

    def "Base hasMethod"() {
        setup:
        def methodResolver = new MethodResolver(Base.class)

        expect:
        methodResolver.hasMethod("doSomething")
        !methodResolver.hasMethod("doSomething2")
        !methodResolver.hasMethod("fooBar")
        methodResolver.hasMethod("toString")
        methodResolver.hasMethod("hashCode")
    }

    def "Derived hasMethod"() {
        setup:
        def methodResolver = new MethodResolver(Derived.class)

        expect:
        methodResolver.hasMethod("doSomething")
        methodResolver.hasMethod("doSomething2")
        !methodResolver.hasMethod("fooBar")
        methodResolver.hasMethod("toString")
        methodResolver.hasMethod("hashCode")
    }

    def "getCompatibleMethod"() {
        setup:
        def methodResolver = new MethodResolver(Derived.class)

        expect:
        methodResolver.getCompatibleMethod("doSomething").isPresent()
        methodResolver.getCompatibleMethod("toString").isPresent()
        methodResolver.getCompatibleMethod("equals", Object.class).isPresent()
        methodResolver.getCompatibleMethod("equals", Integer.class).isPresent()
        methodResolver.getCompatibleMethod("number", Number.class).isPresent()
        methodResolver.getCompatibleMethod("number", Integer.class).isPresent()
        methodResolver.getCompatibleMethod("number", Float.class).isPresent()
        !methodResolver.getCompatibleMethod("hoge").isPresent()
    }


    def "getMethod"() {
        setup:
        def methodResolver = new MethodResolver(Derived.class)

        expect:
        methodResolver.getMethod("doSomething").isPresent()
        methodResolver.getMethod("toString").isPresent()
        methodResolver.getMethod("equals", Object.class).isPresent()
        !methodResolver.getMethod("equals", Integer.class).isPresent()
        methodResolver.getMethod("number", Number.class).isPresent()
        !methodResolver.getMethod("number", Object.class).isPresent()
        !methodResolver.getMethod("number", Object.class, Integer.class).isPresent()
        !methodResolver.getMethod("number", Integer.class).isPresent()
        !methodResolver.getMethod("number", Float.class).isPresent()
        !methodResolver.getMethod("hoge").isPresent()
    }

    @Unroll
    def "isResolvable #source"() {
        setup:
        def methodResolver = new MethodResolver(source)

        expect:
        methodResolver.resolvable == expected

        where:
        source        || expected
        int.class     || (false)
        float.class   || (false)
        double.class  || (false)
        Integer.class || (true)

    }
}

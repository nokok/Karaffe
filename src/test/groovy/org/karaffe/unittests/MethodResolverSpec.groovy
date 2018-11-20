package org.karaffe.unittests

import org.karaffe.compiler.MethodResolver
import spock.lang.Specification

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
    }
}

package org.karaffe.unittests;

import org.karaffe.compiler.util.resolver.MethodResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodResolverTest {

    static class Base {
        public void doSomething() { // Made public for reflection if needed by MethodResolver from different package
        }
    }

    static class Derived extends Base {
        public void doSomething2() { // Made public
        }

        public void number(Number i) { // Made public
        }
    }

    @Test
    void baseHasMethod() {
        MethodResolver methodResolver = new MethodResolver(Base.class);

        assertTrue(methodResolver.hasMethod("doSomething"));
        assertFalse(methodResolver.hasMethod("doSomething2"));
        assertFalse(methodResolver.hasMethod("fooBar"));
        assertTrue(methodResolver.hasMethod("toString")); // from Object
        assertTrue(methodResolver.hasMethod("hashCode")); // from Object
    }

    @Test
    void derivedHasMethod() {
        MethodResolver methodResolver = new MethodResolver(Derived.class);

        assertTrue(methodResolver.hasMethod("doSomething"));
        assertTrue(methodResolver.hasMethod("doSomething2"));
        assertFalse(methodResolver.hasMethod("fooBar"));
        assertTrue(methodResolver.hasMethod("toString"));
        assertTrue(methodResolver.hasMethod("hashCode"));
    }

    @Test
    void getCompatibleMethod() {
        MethodResolver methodResolver = new MethodResolver(Derived.class);

        assertTrue(methodResolver.getCompatibleMethod("doSomething").isPresent());
        assertTrue(methodResolver.getCompatibleMethod("toString").isPresent());
        assertTrue(methodResolver.getCompatibleMethod("equals", Object.class).isPresent());
        assertTrue(methodResolver.getCompatibleMethod("equals", Integer.class).isPresent()); // Object.equals(Object) is compatible
        assertTrue(methodResolver.getCompatibleMethod("number", Number.class).isPresent());
        assertTrue(methodResolver.getCompatibleMethod("number", Integer.class).isPresent()); // Integer is a Number
        assertTrue(methodResolver.getCompatibleMethod("number", Float.class).isPresent());   // Float is a Number
        assertFalse(methodResolver.getCompatibleMethod("hoge").isPresent());
    }

    @Test
    void getMethod() {
        MethodResolver methodResolver = new MethodResolver(Derived.class);

        assertTrue(methodResolver.getMethod("doSomething").isPresent());
        assertTrue(methodResolver.getMethod("toString").isPresent());
        assertTrue(methodResolver.getMethod("equals", Object.class).isPresent());
        assertFalse(methodResolver.getMethod("equals", Integer.class).isPresent()); // Exact match fails
        assertTrue(methodResolver.getMethod("number", Number.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Object.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Object.class, Integer.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Integer.class).isPresent()); // Exact match fails
        assertFalse(methodResolver.getMethod("number", Float.class).isPresent());   // Exact match fails
        assertFalse(methodResolver.getMethod("hoge").isPresent());
    }

    static Stream<Arguments> isResolvableSource() {
        return Stream.of(
            Arguments.of(int.class, false),
            Arguments.of(float.class, false),
            Arguments.of(double.class, false),
            Arguments.of(Integer.class, true)
        );
    }

    @ParameterizedTest(name = "isResolvable {0}")
    @MethodSource("isResolvableSource")
    void isResolvable(Class<?> sourceClass, boolean expected) {
        MethodResolver methodResolver = new MethodResolver(sourceClass);
        // Assuming 'resolvable' property maps to an 'isResolvable()' getter
        assertEquals(expected, methodResolver.isResolvable());
    }
}

package org.karaffe.unittests;

import org.karaffe.compiler.util.resolver.MethodResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MethodResolverTest {

    public static class Base {
        public void doSomething() {
        }
    }

    public static class Derived extends Base {
        public void doSomething2() {
        }

        public void number(Number i) {
        }
    }

    @Test
    void baseHasMethod() {
        MethodResolver methodResolver = new MethodResolver(Base.class);

        assertTrue(methodResolver.hasMethod("doSomething"));
        assertFalse(methodResolver.hasMethod("doSomething2"));
        assertFalse(methodResolver.hasMethod("fooBar"));
        assertTrue(methodResolver.hasMethod("toString"));
        assertTrue(methodResolver.hasMethod("hashCode"));
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
        assertTrue(methodResolver.getCompatibleMethod("equals", Integer.class).isPresent());
        assertTrue(methodResolver.getCompatibleMethod("number", Number.class).isPresent());
        assertTrue(methodResolver.getCompatibleMethod("number", Integer.class).isPresent());
        assertTrue(methodResolver.getCompatibleMethod("number", Float.class).isPresent());
        assertFalse(methodResolver.getCompatibleMethod("hoge").isPresent());
    }

    @Test
    void getMethod() {
        MethodResolver methodResolver = new MethodResolver(Derived.class);

        assertTrue(methodResolver.getMethod("doSomething").isPresent());
        assertTrue(methodResolver.getMethod("toString").isPresent());
        assertTrue(methodResolver.getMethod("equals", Object.class).isPresent());
        assertFalse(methodResolver.getMethod("equals", Integer.class).isPresent()); // In Spock: !methodResolver.getMethod("equals", Integer.class).isPresent()
        assertTrue(methodResolver.getMethod("number", Number.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Object.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Object.class, Integer.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Integer.class).isPresent());
        assertFalse(methodResolver.getMethod("number", Float.class).isPresent());
        assertFalse(methodResolver.getMethod("hoge").isPresent());
    }

    @ParameterizedTest(name = "isResolvableTest {index} => source={0}")
    @MethodSource("isResolvableDataSource")
    void isResolvableTest(Class<?> sourceClass, boolean expected) {
        MethodResolver methodResolver = new MethodResolver(sourceClass);
        assertEquals(expected, methodResolver.isResolvable()); // Assuming getter isResolvable()
    }

    static Stream<Arguments> isResolvableDataSource() {
        return Stream.of(
                Arguments.of(int.class, false),
                Arguments.of(float.class, false),
                Arguments.of(double.class, false),
                Arguments.of(Integer.class, true)
        );
    }
}
